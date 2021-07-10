package com.gdghackathon.monthlychallenges.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.gdghackathon.monthlychallenges.NUM_OF_MISSIONS

@BindingAdapter("set_startDate")
fun TextView.setStartDate(date: String?) {
    date?.let {
        date.substringBefore("T")
        val startDate = "${date.substringBefore("T")} ~"
        text = startDate
    }
}

@BindingAdapter("set_missionCount")
fun TextView.setMissionCount(count: Int?) {
    count?.let {
        val missionCount = "$count/$NUM_OF_MISSIONS"
        text = missionCount
    }
}