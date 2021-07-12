package com.gdghackathon.monthlychallenges.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.NUM_OF_MISSIONS
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.FragmentSetChallengeMissionsBinding
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.ui.adapter.MissionListRecyclerAdapter
import com.gdghackathon.monthlychallenges.utils.setMissionCount
import com.gdghackathon.monthlychallenges.viewmodel.ChallengeViewModel

class SetChallengeMissionsFragment(
        private val sampleChallengeId: Long,
        private val sampleChallengeTitle: String,
) : Fragment() {
    private val challengeViewModel: ChallengeViewModel by lazy {
        ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }

    private lateinit var binding: FragmentSetChallengeMissionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_challenge_missions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sampleChallengeId > -1) {
            challengeViewModel.loadData(sampleChallengeId, sampleChallengeTitle)
        } else {
            challengeViewModel.setChallengeTitle(sampleChallengeTitle)
        }

        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.lifecycleOwner = this
        binding.challengeContents.missionList.layoutManager = GridLayoutManager(context, 5)

        binding.buttonCreateChallenge.setOnClickListener {
            val challenge = binding.challenge ?: Challenge()
            challengeViewModel.createChallenge(challenge)
        }

        binding.buttonLeave.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun subscribeUI() {
        challengeViewModel.challenge.observe(viewLifecycleOwner, { challenge ->
            binding.challenge = challenge
            binding.buttonCreateChallenge.isEnabled = challenge.missionList.size >= NUM_OF_MISSIONS

            with (binding.challengeContents.missionList) {
                val missionList = challenge.missionList.toMutableList()
                adapter = MissionListRecyclerAdapter(missionList).apply {
                    onAddItemClick = {
                        challenge.missionList = it
                        updateUI(challenge)
                    }
                }
            }
        })
        challengeViewModel.challengeId.observe(viewLifecycleOwner, {
            // challengeId 설정 & 액티비티 이동
        })
    }

    private fun updateUI(challenge: Challenge) {
        val missionList = challenge.missionList
        binding.challengeContents.tvMissionNumber.setMissionCount(challenge)
        binding.buttonCreateChallenge.isEnabled = missionList.size >= NUM_OF_MISSIONS

        binding.challenge?.missionList = missionList
    }
}