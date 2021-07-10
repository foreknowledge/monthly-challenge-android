package com.gdghackathon.monthlychallenges.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.ActivityMainBinding
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
}