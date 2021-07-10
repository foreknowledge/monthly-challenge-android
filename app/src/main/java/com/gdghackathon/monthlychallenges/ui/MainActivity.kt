package com.gdghackathon.monthlychallenges.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.ActivityMainBinding
import com.gdghackathon.monthlychallenges.model.Challenge
import com.gdghackathon.monthlychallenges.viewmodel.SampleChallengesViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val sampleChallengesViewModel by lazy {
        ViewModelProvider(this).get(SampleChallengesViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonCreateChallenge.setOnClickListener(this)
        binding.button1.setOnClickListener(this)

//        sampleChallengesViewModel.loadData(getAllSampleChallenges())
        sampleChallengesViewModel.loadData()
        sampleChallengesViewModel.sampleChallenges.observe(this, {
            binding.sampleList = it
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_create_challenge -> {
                val intent = Intent(this, CreateChallengeActivity::class.java)
                startActivity(intent)
            }
            R.id.button_1 -> { }
            else -> { }
        }
    }

    private fun getAllSampleChallenges() = listOf(
            Challenge(0, resources.getString(R.string.main_button1_text), 0, ""),
            Challenge(1, resources.getString(R.string.main_button2_text), 0, ""),
            Challenge(2, resources.getString(R.string.main_button3_text), 0, ""),
            Challenge(3, resources.getString(R.string.main_button4_text), 0, ""),
            Challenge(4, resources.getString(R.string.main_button5_text), 0, ""),
            Challenge(5, resources.getString(R.string.main_button6_text), 0, ""),
            Challenge(6, resources.getString(R.string.main_button7_text), 0, ""),
            Challenge(7, resources.getString(R.string.main_button8_text), 0,"")
    )
}