package com.gdghackathon.monthlychallenges.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.SAMPLE_CHALLENGE_ID
import com.gdghackathon.monthlychallenges.SAMPLE_CHALLENGE_TITLE

class CreateChallengeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_challenge)

        val sampleChallengeId = intent.getLongExtra(SAMPLE_CHALLENGE_ID, -1)
        val sampleChallengeTitle = intent.getStringExtra(SAMPLE_CHALLENGE_TITLE) ?: ""

        val transaction = supportFragmentManager
                .beginTransaction()
                .add(R.id.layout_container, SetChallengeTitleFragment(sampleChallengeId, sampleChallengeTitle))
        transaction.commit()
    }
}