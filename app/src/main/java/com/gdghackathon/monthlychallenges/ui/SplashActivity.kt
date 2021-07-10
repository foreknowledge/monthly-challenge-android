package com.gdghackathon.monthlychallenges.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gdghackathon.monthlychallenges.GlobalApp
import com.gdghackathon.monthlychallenges.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalApp.challengeId =
            getPreferences(MODE_PRIVATE).getLong(getString(R.string.shared_pref_key), -1L)

        Log.i("challengeId", GlobalApp.challengeId.toString())

        Handler().postDelayed({
            val intent = if (GlobalApp.challengeId == null || GlobalApp.challengeId == -1L) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, TestActivity::class.java)
                //intent = Intent(this, ChallengeContentsActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 2000)
    }
}