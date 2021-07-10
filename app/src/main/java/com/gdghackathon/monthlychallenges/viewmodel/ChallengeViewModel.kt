package com.gdghackathon.monthlychallenges.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.model.ChallengeRepository
import kotlinx.coroutines.launch

class ChallengeViewModel : ViewModel() {
    private val repository = ChallengeRepository

    private val _challenge = MutableLiveData<Challenge>()
    val challenge: LiveData<Challenge> = _challenge

    fun loadData(challengeId: Long) = viewModelScope.launch {
        val challenge = repository.getChallenge(challengeId)
        _challenge.value = challenge
    }
}