package com.gdghackathon.monthlychallenges.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.ActivityChallengeContentsBinding
import com.gdghackathon.monthlychallenges.viewmodel.ChallengeViewModel

class ChallengeContentsActivity : AppCompatActivity() {
    private val challengeViewModel by lazy {
        ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }

    private lateinit var binding: ActivityChallengeContentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_challenge_contents)

        challengeViewModel.loadData(1)

        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.lifecycleOwner = this
        binding.missionList.layoutManager = GridLayoutManager(this@ChallengeContentsActivity, 5)
    }

    private fun subscribeUI() {
        challengeViewModel.challenge.observe(this, {
            binding.challenge = it

            val missionList = it.missionList?.toMutableList() ?: mutableListOf()
            binding.missionList.adapter = MissionListRecyclerAdapter(missionList, editable = false).apply {
                setOnItemClickListener {
                    // 인증 or 자세히 보기
                }
            }
            binding.missionList.adapter?.notifyDataSetChanged()
        })
    }
}