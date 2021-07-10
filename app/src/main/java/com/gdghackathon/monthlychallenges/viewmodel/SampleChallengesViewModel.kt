package com.gdghackathon.monthlychallenges.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.model.ChallengeRepository

class SampleChallengesViewModel : ViewModel() {
    private val repository = ChallengeRepository

    private val _sampleChallenges = MutableLiveData<List<Challenge>>()
    val sampleChallenges: LiveData<List<Challenge>> = _sampleChallenges

    fun loadData(challengeList: List<Challenge>) {
        _sampleChallenges.value = challengeList
    }
}