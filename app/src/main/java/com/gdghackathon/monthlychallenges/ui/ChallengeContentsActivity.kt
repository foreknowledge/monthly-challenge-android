package com.gdghackathon.monthlychallenges.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.R
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

//        challengeViewModel.loadData(1)

        setupUI()
        subscribeUI()
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
}