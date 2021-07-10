package com.gdghackathon.monthlychallenges.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.ActivityTestBinding
import com.gdghackathon.monthlychallenges.model.Mission
import com.gdghackathon.monthlychallenges.utils.ToastUtil

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)

        binding.lifecycleOwner = this
        binding.missionList.apply {
            layoutManager = GridLayoutManager(this@TestActivity, 5)
            adapter = MissionListRecyclerAdapter(getMissionList()).apply {
                setOnItemClickListener { ToastUtil.showToast(this@TestActivity, it.name) }
            }
        }
    }

    private fun getMissionList(): MutableList<Mission> = mutableListOf(
            Mission(1, "mission1", false),
            Mission(2, "mission2", false),
            Mission(3, "mission3", false),
            Mission(4, "mission4", false),
            Mission(1, "mission1", false),
            Mission(2, "mission2", false),
            Mission(3, "mission3", false),
            Mission(4, "mission4", false),
            Mission(1, "mission1", false),
            Mission(2, "mission2", false),
            Mission(3, "mission3", false),
            Mission(4, "mission4", false),
            Mission(1, "mission1", false),
            Mission(2, "mission2", false),
            Mission(3, "mission3", false),
            Mission(4, "mission4", false),
            Mission(1, "mission1", false),
            Mission(2, "mission2", false),
            Mission(3, "mission3", false),
            Mission(4, "mission4", false),
            Mission(1, "mission1", false),
            Mission(2, "mission2", false),
            Mission(3, "mission3", false),
            Mission(4, "mission4", false),
            Mission(1, "mission1", false),
            Mission(2, "mission2", false),
    )
}