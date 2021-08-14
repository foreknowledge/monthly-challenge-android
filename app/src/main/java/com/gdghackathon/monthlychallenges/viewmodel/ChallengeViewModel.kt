package com.gdghackathon.monthlychallenges.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdghackathon.monthlychallenges.NUM_OF_MISSIONS
import com.gdghackathon.monthlychallenges.RESPONSE_CODE_OK
import com.gdghackathon.monthlychallenges.model.ChallengeRepository
import com.gdghackathon.monthlychallenges.model.data.Challenge
import com.gdghackathon.monthlychallenges.model.data.ChallengeRequest
import com.gdghackathon.monthlychallenges.utils.FormFileUtil
import kotlinx.coroutines.launch
import java.io.File

class ChallengeViewModel : ViewModel() {
    private val repository = ChallengeRepository

    private val _challenge = MutableLiveData<Challenge>()
    val challenge: LiveData<Challenge> = _challenge

    private val _challengeId = MutableLiveData<Long>()
    val challengeId: LiveData<Long> = _challengeId

    private val _missionUpdated = MutableLiveData<Boolean>()
    val missionUpdated: LiveData<Boolean> = _missionUpdated

    fun loadData(challengeId: Long) = viewModelScope.launch {
        val response = repository.getChallenge(challengeId)
        if (response?.code() == RESPONSE_CODE_OK) {
            _challenge.value = response.body()
        }
    }

    fun loadMissions(challengeId: Long, challengeTitle: String) = viewModelScope.launch {
        val response = repository.getChallenge(challengeId)
        if (response?.code() == RESPONSE_CODE_OK) {
            val challenge = response.body() ?: return@launch
            _challenge.value = Challenge(name = challengeTitle, missionList = challenge.missionList)
        }
    }

    fun createChallenge(challenge: Challenge) = viewModelScope.launch {
        if (challenge.missionList.size != NUM_OF_MISSIONS) return@launch

        val challengeRequest = ChallengeRequest(challenge.name, challenge.missionList)
        val response = repository.createChallenge(challengeRequest)
        if (response?.code() == RESPONSE_CODE_OK) {
            _challengeId.value = response.body()?.id
        }
    }

    fun deleteChallenge(challengeId: Long) = viewModelScope.launch {
        repository.deleteChallenge(challengeId)
        _challengeId.value = -1
    }

    fun completeMission(challengeId: Long, missionId: Long, imagePath: String?, memo: String?) = viewModelScope.launch {
        val filePart = if (imagePath != null) {
            val file = File(imagePath)
            FormFileUtil.getImageBody("file", file)
        } else null

        val memoPart = FormFileUtil.getBody("memo", memo ?: "")

        val response = repository.completeMission(challengeId, missionId, filePart, memoPart)
        if (response?.code() == RESPONSE_CODE_OK) {
            _missionUpdated.value = true
        }
    }
}