package com.gdghackathon.monthlychallenges.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.gdghackathon.monthlychallenges.EXTRA_CHALLENGE_ID
import com.gdghackathon.monthlychallenges.GlobalApp
import com.gdghackathon.monthlychallenges.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalApp.challengeId =
            getSharedPreferences(getString(R.string.shared_pref_key), MODE_PRIVATE)
                .getLong(getString(R.string.shared_pref_challenge_id), -1L)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (GlobalApp.challengeId == -1L) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, ChallengeContentsActivity::class.java).apply {
                    putExtra(EXTRA_CHALLENGE_ID, GlobalApp.challengeId)
                }
            }

            startActivity(intent)
            finish()
        }, 2000)
    }
}