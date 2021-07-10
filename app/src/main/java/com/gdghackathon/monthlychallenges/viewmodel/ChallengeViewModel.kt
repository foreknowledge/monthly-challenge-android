package com.gdghackathon.monthlychallenges.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.model.ChallengeRepository

class ChallengeViewModel : ViewModel() {
    private val repository = ChallengeRepository

    private val _challenge = MutableLiveData<Challenge>()
    val challenge: LiveData<Challenge> = _challenge
}