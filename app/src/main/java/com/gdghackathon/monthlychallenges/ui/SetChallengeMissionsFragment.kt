package com.gdghackathon.monthlychallenges.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.EXTRA_CHALLENGE_ID
import com.gdghackathon.monthlychallenges.GlobalApp
import com.gdghackathon.monthlychallenges.NUM_OF_MISSIONS
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.FragmentSetChallengeMissionsBinding
import com.gdghackathon.monthlychallenges.model.data.Challenge
import com.gdghackathon.monthlychallenges.ui.adapter.MissionListRecyclerAdapter
import com.gdghackathon.monthlychallenges.utils.changeImage
import com.gdghackathon.monthlychallenges.utils.setMissionCount
import com.gdghackathon.monthlychallenges.viewmodel.ChallengeViewModel

class SetChallengeMissionsFragment(
        sampleChallengeId: Long,
        sampleChallengeTitle: String,
) : Fragment() {
    private val challengeViewModel: ChallengeViewModel by lazy {
        ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }

    private var currentChallenge = Challenge(id = sampleChallengeId, name = sampleChallengeTitle)

    private lateinit var binding: FragmentSetChallengeMissionsBinding
    private lateinit var missionListAdapter: MissionListRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_challenge_missions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (currentChallenge.id > -1) {
            challengeViewModel.loadMissions(currentChallenge.id, currentChallenge.name)
        } else {
            binding.challengeContents.challenge = currentChallenge
            initMissionListRecyclerView()
        }

        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.lifecycleOwner = this
        binding.challengeContents.missionList.layoutManager = GridLayoutManager(context, 5)

        binding.buttonCreateChallenge.setOnClickListener {
            currentChallenge.missionList = missionListAdapter.missionList
            challengeViewModel.createChallenge(currentChallenge)
        }

        binding.buttonLeave.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun subscribeUI() {
        challengeViewModel.challenge.observe(viewLifecycleOwner, { challenge ->
            currentChallenge = challenge
            binding.challengeContents.challenge = challenge
            binding.buttonCreateChallenge.isEnabled = challenge.missionList.size >= NUM_OF_MISSIONS

            initMissionListRecyclerView()
        })
        challengeViewModel.challengeId.observe(viewLifecycleOwner, {
            GlobalApp.setAndSaveChallengeId(requireActivity(), it)
            val intent = Intent(context, ChallengeContentsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra(EXTRA_CHALLENGE_ID, it)
            startActivity(intent)
        })
    }

    private fun initMissionListRecyclerView() {
        val missionList = currentChallenge.missionList.toMutableList()
        missionListAdapter = MissionListRecyclerAdapter(missionList).apply {
            onAddItemClick = {
                currentChallenge.missionList = it
                updateUI()
            }
        }
        binding.challengeContents.missionList.adapter = missionListAdapter
    }

    private fun updateUI() {
        with (binding.challengeContents) {
            imageviewChalliney.changeImage(currentChallenge.missionCount)
            tvMissionNumber.setMissionCount(currentChallenge)

            missionList.scrollToPosition(missionListAdapter.itemCount - 1)
        }

        binding.buttonCreateChallenge.isEnabled = currentChallenge.missionList.size >= NUM_OF_MISSIONS
    }
}