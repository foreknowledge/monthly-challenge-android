package com.gdghackathon.monthlychallenges.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.viewmodel.ChallengeListViewModel

class MainActivity : AppCompatActivity() {
    private val challengeListViewModel by lazy {
        ViewModelProvider(this).get(ChallengeListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val challengeList = challengeListViewModel.challengeList.value ?: listOf()
        challengeList.forEach {
            Log.d("test", "${it.id}, ${it.name}, ${it.missionCount}")
        }
    }
}