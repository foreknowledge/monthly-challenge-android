package com.gdghackathon.monthlychallenges

import android.app.Activity
import android.app.Application

class GlobalApp : Application() {
    companion object {
        var challengeId: Long? = null

        fun saveChallengeId(activity: Activity) {
            val sharedPref = activity.getSharedPreferences(activity.getString(R.string.app_name), MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putLong(activity.getString(R.string.shared_pref_key), challengeId!!)
            editor.apply()
        }
    }
}