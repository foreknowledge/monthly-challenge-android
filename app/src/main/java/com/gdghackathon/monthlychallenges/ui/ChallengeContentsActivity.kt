package com.gdghackathon.monthlychallenges.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.*
import com.gdghackathon.monthlychallenges.databinding.ActivityChallengeContentsBinding
import com.gdghackathon.monthlychallenges.ui.adapter.MissionListRecyclerAdapter
import com.gdghackathon.monthlychallenges.utils.BitmapUtil
import com.gdghackathon.monthlychallenges.utils.FileUtil
import com.gdghackathon.monthlychallenges.utils.ToastUtil
import com.gdghackathon.monthlychallenges.viewmodel.ChallengeViewModel
import java.io.File

class ChallengeContentsActivity : AppCompatActivity() {
    private val challengeViewModel by lazy {
        ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }

    private var challengeId: Long = 1

    private lateinit var binding: ActivityChallengeContentsBinding

    private lateinit var imageView: ImageView
    private lateinit var uploadButton: Button
    private lateinit var editMemo: EditText

    private var imageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_challenge_contents)

        challengeId = intent.getLongExtra(EXTRA_CHALLENGE_ID, 1)

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

            with(binding.challengeContents.missionList) {
                val missionList = it.missionList.toMutableList()
                adapter = MissionListRecyclerAdapter(missionList, editable = false).apply {
                    setOnItemClickListener { mission ->
                        // 인증 or 자세히 보기
                        if (mission.missionCheck) {
                            val intent = Intent(this@ChallengeContentsActivity, DetailMissionActivity::class.java)
                            intent.putExtra(EXTRA_MISSION, mission)
                            startActivity(intent)
                        } else {
                            uploadPhoto(mission.id)
                        }
                    }
                }
                adapter?.notifyDataSetChanged()
            }

            hideProgress()
        })

        challengeViewModel.challengeId.observe(this, {
            if (it == -1L) {
                GlobalApp.setAndSaveChallengeId(this, it)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })

        challengeViewModel.missionUpdated.observe(this, {
            if (it) {
                challengeViewModel.loadData(challengeId)
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
        uploadButton = view.findViewById(R.id.button_upload_image)
        val noUploadButton = view.findViewById<TextView>(R.id.textview_no_upload)

        imageView.setOnClickListener {
            checkCameraPermission()
        }

        uploadButton.setOnClickListener {
            writeMemo(missionId)
            alertDialog.dismiss()
        }

        noUploadButton.setOnClickListener {
            writeMemo(missionId)
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun writeMemo(missionId: Long) {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.dialog_write_memo, null)

        val alertDialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        editMemo = view.findViewById(R.id.edittext_memo)
        val writeButton = view.findViewById<Button>(R.id.button_write_memo)
        val noWriteButton = view.findViewById<TextView>(R.id.textview_no_write_memo)

        editMemo.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    writeButton.isEnabled = p0.isNotEmpty()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        writeButton.setOnClickListener {
            completeMission(missionId, true)
            alertDialog.dismiss()
        }

        noWriteButton.setOnClickListener {
            completeMission(missionId, false)
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun completeMission(missionId: Long, isWrite: Boolean) {
        val memo = if (isWrite) {
            editMemo.editableText.toString()
        } else {
            ""
        }

        challengeViewModel.completeMission(GlobalApp.challengeId, missionId, imageFile?.absolutePath, memo)

        // 이미지 파일 초기화
        imageFile = null

        showProgress()
    }

    private fun checkCameraPermission() =
        checkPermission(Manifest.permission.CAMERA, REQUEST_CAMERA_PERMISSION_CODE)

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(permission), requestCode)
        } else {
            switchToCamera()
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
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    ToastUtil.showToast(this, "Permission granted")
                    switchToCamera()
                } else {
                    ToastUtil.showToast(this, "Permission denied")
                }
                return
            }
            else -> { /* ignored */ }
        }
    }

    private fun switchToCamera() {
        imageFile = FileUtil.createJpgFileExternal(this)

        // uri 데이터 형태 = content://{authority}/{파일의 디렉토리}/{파일명}
        val uri = FileProvider.getUriForFile(this, "${packageName}.fileprovider", imageFile!!)

        Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            .apply { putExtra(MediaStore.EXTRA_OUTPUT, uri) }
            .also { startActivityForResult(it, REQUEST_IMAGE_CAPTURE) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = imageFile?.absolutePath?.let { BitmapUtil.getBitmap(it) }

            imageView.setImageBitmap(imageBitmap)
            uploadButton.isEnabled = true
        }
    }

    private fun showProgress() { binding.progressCircular.visibility = View.VISIBLE }
    private fun hideProgress() { binding.progressCircular.visibility = View.GONE }
}