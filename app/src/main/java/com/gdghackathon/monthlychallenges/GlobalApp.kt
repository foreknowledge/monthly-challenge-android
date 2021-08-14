package com.gdghackathon.monthlychallenges

import android.app.Activity
import android.app.Application

class GlobalApp : Application() {
    companion object {
        var challengeId: Long = -1

        fun setAndSaveChallengeId(activity: Activity, challengeId: Long) {
            this.challengeId = challengeId

            val sharedPref = activity.getSharedPreferences(activity.getString(R.string.shared_pref_key), MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putLong(activity.getString(R.string.shared_pref_challenge_id), challengeId)
            editor.apply()
        }
    }
}