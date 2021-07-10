package com.gdghackathon.monthlychallenges.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.viewmodel.SampleChallengesViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val sampleChallengesViewModel by lazy {
        ViewModelProvider(this).get(SampleChallengesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createChallengeButton = findViewById<Button>(R.id.button_create_challenge)
        val sample1Button = findViewById<Button>(R.id.button_1)
        val sample2Button = findViewById<Button>(R.id.button_2)
        val sample3Button = findViewById<Button>(R.id.button_3)
        val sample4Button = findViewById<Button>(R.id.button_4)
        val sample5Button = findViewById<Button>(R.id.button_5)
        val sample6Button = findViewById<Button>(R.id.button_6)
        val sample7Button = findViewById<Button>(R.id.button_7)
        val sample8Button = findViewById<Button>(R.id.button_8)
        val completeChallengeButton = findViewById<ImageButton>(R.id.imagebutton_complete_challenge_list)

        createChallengeButton.setOnClickListener(this)
        sample1Button.setOnClickListener(this)
        sample2Button.setOnClickListener(this)
        sample3Button.setOnClickListener(this)
        sample4Button.setOnClickListener(this)
        sample5Button.setOnClickListener(this)
        sample6Button.setOnClickListener(this)
        sample7Button.setOnClickListener(this)
        sample8Button.setOnClickListener(this)
        completeChallengeButton.setOnClickListener(this)

        val sampleChallenges = sampleChallengesViewModel.sampleChallenges.value
            ?: listOf(
                Challenge(1, "소확도", 0),
                Challenge(2, "극뽁", 0),
                Challenge(3, "운동", 0),
            )
        sampleChallenges.forEach {
            Log.d("test", "${it.id}, ${it.name}, ${it.missionCount}")
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_create_challenge -> {
                val intent = Intent(this, CreateChallengeActivity::class.java)
                startActivity(intent)
            }
            R.id.button_1 -> { }
            R.id.button_2 -> { }
            R.id.button_3 -> { }
            R.id.button_4 -> { }
            R.id.button_5 -> { }
            R.id.button_6 -> { }
            R.id.button_7 -> { }
            R.id.button_8 -> { }
            R.id.imagebutton_complete_challenge_list -> { }
            else -> { }
        }
    }
}