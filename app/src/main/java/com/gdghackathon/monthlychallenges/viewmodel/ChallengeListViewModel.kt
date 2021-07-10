package com.gdghackathon.monthlychallenges.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.model.ChallengeRepository
import kotlinx.coroutines.launch

class ChallengeListViewModel : ViewModel() {
    private val repository = ChallengeRepository

    private val _challengeList = MutableLiveData<List<Challenge>>()
    val challengeList: LiveData<List<Challenge>> = _challengeList
}