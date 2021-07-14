package com.gdghackathon.monthlychallenges.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.*
import com.gdghackathon.monthlychallenges.databinding.ActivityChallengeContentsBinding
import com.gdghackathon.monthlychallenges.ui.adapter.MissionListRecyclerAdapter
import com.gdghackathon.monthlychallenges.viewmodel.ChallengeViewModel

class ChallengeContentsActivity : AppCompatActivity() {
    private val challengeViewModel by lazy {
        ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }

    private var challengeId: Long = 1

    private lateinit var binding: ActivityChallengeContentsBinding

    private lateinit var imageView: ImageView
    private lateinit var editMemo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_challenge_contents)

        challengeId = intent.getLongExtra(EXTRA_CHALLENGE_ID, 1)

        Log.i("test", "challenge id = $challengeId")
        challengeViewModel.loadData(challengeId)

        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.lifecycleOwner = this
        binding.challengeContents.missionList.layoutManager = GridLayoutManager(this@ChallengeContentsActivity, 5)

        binding.tvStopChallenge.setOnClickListener {
            showDeleteChallengePopup()
        }
    }

    private fun showDeleteChallengePopup() = AlertDialog.Builder(this)
        .setTitle(getString(R.string.str_stop_challenge))
        .setMessage(getString(R.string.str_stop_challenge_desc))
        .setPositiveButton(R.string.dialog_yes) { _, _ ->
            challengeViewModel.deleteChallenge(challengeId)
        }
        .setNegativeButton(R.string.dialog_no) { dialog, _ -> dialog.dismiss() }
        .create()
        .show()

    private fun subscribeUI() {
        challengeViewModel.challenge.observe(this, {
            binding.challengeContents.challenge = it

            with (binding.challengeContents.missionList) {
                val missionList = it.missionList.toMutableList()
                adapter = MissionListRecyclerAdapter(missionList, editable = false).apply {
                    setOnItemClickListener { mission ->
                        // 인증 or 자세히 보기
                        if (mission.missionCheck) {
                            val intent = Intent(this@ChallengeContentsActivity, DetailMissionActivity::class.java)
                            startActivity(intent)
                        } else {
                            uploadPhoto(mission.id)
                        }
                    }
                }
                adapter?.notifyDataSetChanged()
            }
        })

        challengeViewModel.challengeId.observe(this, {
            if (it == -1L) {
                GlobalApp.setAndSaveChallengeId(this, it)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    private fun uploadPhoto(missionId: Long) {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.dialog_upload_photo, null)

        val alertDialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        imageView = view.findViewById(R.id.imageview_add_image)
        val uploadButton = view.findViewById<Button>(R.id.button_upload_image)
        val noUploadButton = view.findViewById<TextView>(R.id.textview_no_upload)

        imageView.setOnClickListener {
            checkCameraPermission()
            dispatchTakePictureIntent()
        }

        uploadButton.setOnClickListener {
            writeMemo(missionId, true)
            alertDialog.dismiss()
        }

        noUploadButton.setOnClickListener {
            writeMemo(missionId, false)
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun writeMemo(missionId: Long, isUpload: Boolean) {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.dialog_write_memo, null)

        val alertDialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        editMemo = view.findViewById<EditText>(R.id.edittext_memo)
        val writeButton = view.findViewById<Button>(R.id.button_write_memo)
        val noWriteButton = view.findViewById<TextView>(R.id.textview_no_write_memo)

        writeButton.setOnClickListener {
            completeMission(missionId, isUpload, true)
            alertDialog.dismiss()
        }

        noWriteButton.setOnClickListener {
            completeMission(missionId, isUpload, false)
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun completeMission(missionId: Long, isUpload: Boolean, isWrite: Boolean) {
        val image = if (isUpload) {
            (imageView.drawable as BitmapDrawable).bitmap
        } else {
            null
        }

        val memo = if (isWrite) {
            editMemo.editableText.toString()
        } else {
            null
        }

        challengeViewModel.completeMission(GlobalApp.challengeId, missionId, image, memo)
    }

    private fun checkCameraPermission() =
        checkPermission(Manifest.permission.CAMERA, REQUEST_CAMERA_PERMISSION_CODE)

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CAMERA_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> { /* ignored */ }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            imageView.setImageBitmap(imageBitmap)
        }
    }
}