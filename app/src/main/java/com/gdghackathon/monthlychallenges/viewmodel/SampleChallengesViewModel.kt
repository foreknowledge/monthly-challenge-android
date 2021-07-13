package com.gdghackathon.monthlychallenges.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdghackathon.monthlychallenges.model.ChallengeRepository
import com.gdghackathon.monthlychallenges.model.data.Challenge
import kotlinx.coroutines.launch

class SampleChallengesViewModel : ViewModel() {
    private val repository = ChallengeRepository

    private val _sampleChallenges = MutableLiveData<List<Challenge>>()
    val sampleChallenges: LiveData<List<Challenge>> = _sampleChallenges

    fun loadData() = viewModelScope.launch {
        val sampleList = repository.getAllSampleChallenges()
        _sampleChallenges.value = sampleList
    }
}