package com.gdghackathon.monthlychallenges

import android.app.Activity
import android.app.Application
import android.content.Context

class GlobalApp : Application() {
    companion object {
        var challengeId: Long? = null
    }

    override fun onTerminate() {
        super.onTerminate()

        val sharedPref = Activity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putLong(getString(R.string.app_name), challengeId!!)
        editor.apply()
    }
}