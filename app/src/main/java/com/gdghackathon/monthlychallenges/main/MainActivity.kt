package com.gdghackathon.monthlychallenges.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.viewmodel.SampleChallengesViewModel

class MainActivity : AppCompatActivity() {
    private val challengeListViewModel by lazy {
        ViewModelProvider(this).get(SampleChallengesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val challengeList = challengeListViewModel.sampleChallenges.value
            ?: listOf(
                Challenge(1, "소확도", 0),
                Challenge(2, "극뽁", 0),
                Challenge(3, "운동", 0),
            )
        challengeList.forEach {
            Log.d("test", "${it.id}, ${it.name}, ${it.missionCount}")
        }
    }
}