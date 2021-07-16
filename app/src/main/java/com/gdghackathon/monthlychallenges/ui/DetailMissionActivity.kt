package com.gdghackathon.monthlychallenges.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.ActivityDetailMissionBinding

class DetailMissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_mission)
    }
}