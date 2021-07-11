package com.gdghackathon.monthlychallenges.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.SAMPLE_CHALLENGE_ID
import com.gdghackathon.monthlychallenges.SAMPLE_CHALLENGE_TITLE
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
            R.id.button_1 -> {
                val sampleChallengeId = v.tag as Long
                val sampleChallengeTitle = (v as? Button)?.text
                val intent = Intent(this, CreateChallengeActivity::class.java)
                intent.putExtra(SAMPLE_CHALLENGE_ID, sampleChallengeId)
                intent.putExtra(SAMPLE_CHALLENGE_TITLE, sampleChallengeTitle)
                startActivity(intent)
            }
            else -> { }
        }
    }
}