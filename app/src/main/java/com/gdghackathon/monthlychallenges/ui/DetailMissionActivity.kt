package com.gdghackathon.monthlychallenges.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gdghackathon.monthlychallenges.EXTRA_MISSION
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.ActivityDetailMissionBinding
import com.gdghackathon.monthlychallenges.model.data.Mission

class DetailMissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_mission)

        val mission = intent.getSerializableExtra(EXTRA_MISSION) as? Mission

        binding.lifecycleOwner = this
        binding.mission = mission
    }
}