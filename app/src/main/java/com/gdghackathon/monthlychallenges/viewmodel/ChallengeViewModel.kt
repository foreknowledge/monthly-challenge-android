package com.gdghackathon.monthlychallenges.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdghackathon.monthlychallenges.NUM_OF_MISSIONS
import com.gdghackathon.monthlychallenges.RESPONSE_CODE_OK
import com.gdghackathon.monthlychallenges.model.ChallengeRepository
import com.gdghackathon.monthlychallenges.model.data.Challenge
import com.gdghackathon.monthlychallenges.model.data.ChallengeRequest
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
        repository.getChallenge(challengeId).let {
            _challenge.value = Challenge(name = challengeTitle, missionList = it.missionList)
        }
    }

    fun createChallenge(challenge: Challenge) = viewModelScope.launch {
        if (challenge.missionList.size == NUM_OF_MISSIONS) {
            val challengeRequest = ChallengeRequest(challenge.name, challenge.missionList)
            val response = repository.createChallenge(challengeRequest)
            if (response.code() == RESPONSE_CODE_OK) {
                _challengeId.value = response.body()?.id
            }
        }
    }
    
    fun deleteChallenge(challengeId: Long) = viewModelScope.launch {
        repository.deleteChallenge(challengeId)
        _challengeId.value = -1
    }
      
    fun completeMission(challengeId: Long, missionId: Long, image: Bitmap?, memo: String) = viewModelScope.launch {
        repository.completeMission(challengeId, missionId, image, memo)
    }
}