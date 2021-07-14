package com.gdghackathon.monthlychallenges.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.EXTRA_CHALLENGE_ID
import com.gdghackathon.monthlychallenges.GlobalApp
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.REQUEST_CAMERA_PERMISSION_CODE
import com.gdghackathon.monthlychallenges.databinding.ActivityChallengeContentsBinding
import com.gdghackathon.monthlychallenges.ui.adapter.MissionListRecyclerAdapter
import com.gdghackathon.monthlychallenges.viewmodel.ChallengeViewModel

class ChallengeContentsActivity : AppCompatActivity() {
    private val challengeViewModel by lazy {
        ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }

    private lateinit var binding: ActivityChallengeContentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_challenge_contents)

        val challengeId = intent.getLongExtra(EXTRA_CHALLENGE_ID, 1)

        Log.d("test", "challenge id = $challengeId")
        challengeViewModel.loadData(challengeId)

        setupUI()
        subscribeUI()
    }

    override fun onDestroy() {
        super.onDestroy()

        GlobalApp.saveChallengeId(this)
    }

    private fun setupUI() {
        binding.lifecycleOwner = this
        binding.challengeContents.missionList.layoutManager = GridLayoutManager(this@ChallengeContentsActivity, 5)
    }

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
                            uploadPhoto()
                        }
                    }
                }
                adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun uploadPhoto() {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.dialog_upload_photo, null)

        val alertDialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        val imageView = view.findViewById<ImageView>(R.id.imageview_add_image)
        val uploadButton = view.findViewById<Button>(R.id.button_upload_image)
        val noUploadButton = view.findViewById<TextView>(R.id.textview_no_upload)

        imageView.setOnClickListener {
            TODO("사진 촬영, 사진첩 열기")
        }

        uploadButton.setOnClickListener {
            TODO("사진 업로드")

            writeMemo()
            alertDialog.dismiss()
        }

        noUploadButton.setOnClickListener {
            TODO("사진 미업로드")

            writeMemo()
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun writeMemo() {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.dialog_write_memo, null)

        val alertDialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        val editMemo = view.findViewById<EditText>(R.id.edittext_memo)
        val writeButton = view.findViewById<Button>(R.id.button_write_memo)
        val noWriteButton = view.findViewById<TextView>(R.id.textview_no_write_memo)

        writeButton.setOnClickListener {
            val memo = editMemo.editableText.toString()

            TODO("메모 업로드")

            val intent = Intent(this, DetailMissionActivity::class.java)
            startActivity(intent)

            alertDialog.dismiss()
        }

        noWriteButton.setOnClickListener {
            TODO("메모 미업로드")

            val intent = Intent(this, DetailMissionActivity::class.java)
            startActivity(intent)

            alertDialog.dismiss()
        }

        alertDialog.show()
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
}