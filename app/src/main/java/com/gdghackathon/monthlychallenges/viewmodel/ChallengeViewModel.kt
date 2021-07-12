package com.gdghackathon.monthlychallenges.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdghackathon.monthlychallenges.NUM_OF_MISSIONS
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.model.ChallengeRepository
import kotlinx.coroutines.launch

class ChallengeViewModel : ViewModel() {
    private val repository = ChallengeRepository

    private val _challenge = MutableLiveData<Challenge>()
    val challenge: LiveData<Challenge> = _challenge

    private val _challengeId = MutableLiveData<Long>()
    val challengeId: LiveData<Long> = _challengeId

    fun loadData(challengeId: Long) = viewModelScope.launch {
        _challenge.value = repository.getChallenge(challengeId)
    }

    fun loadMissions(challengeId: Long, challengeTitle: String) = viewModelScope.launch {
        repository.getChallenge(challengeId)?.let {
            _challenge.value = Challenge(name = challengeTitle, missionList = it.missionList)
        }
    }

    fun createChallenge(challenge: Challenge) = viewModelScope.launch {
        if (challenge.missionList.size == NUM_OF_MISSIONS) {
            val challengeId = repository.createChallenge(challenge.name, challenge.missionList)
            _challengeId.value = challengeId
        }
    }
}