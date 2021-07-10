package com.gdghackathon.monthlychallenges.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.FragmentSetChallengeMissionsBinding
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.viewmodel.ChallengeViewModel

class SetChallengeMissionsFragment : Fragment() {
    private val challengeViewModel: ChallengeViewModel by lazy {
        ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }

    private lateinit var binding: FragmentSetChallengeMissionsBinding
    private val currentChallenge = Challenge()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_challenge_missions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO - 초기 데이터 세팅
        //  1. 직접 등록 - 넘어온 title 있으면 세팅
        //  2. 샘플 등록 - challengeId 있으면 조회해서 세팅

        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.lifecycleOwner = this
        binding.challenge = currentChallenge

        binding.challengeContents.missionList.layoutManager = GridLayoutManager(context, 5)

        binding.buttonCreateChallenge.setOnClickListener {
            challengeViewModel.createChallenge(currentChallenge)
        }
    }

    private fun subscribeUI() {
        challengeViewModel.challenge.observe(viewLifecycleOwner, {
            binding.challenge = it

            with (binding.challengeContents.missionList) {
                val missionList = it.missionList?.toMutableList() ?: mutableListOf()
                adapter = MissionListRecyclerAdapter(missionList)
            }
        })
        challengeViewModel.challengeId.observe(viewLifecycleOwner, {
            // challengeId 설정 & 액티비티 이동
        })
    }
}