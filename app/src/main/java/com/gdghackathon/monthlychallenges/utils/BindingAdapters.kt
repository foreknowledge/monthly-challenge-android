package com.gdghackathon.monthlychallenges.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.gdghackathon.monthlychallenges.BOUNDARY_CHALLINEY_1
import com.gdghackathon.monthlychallenges.BOUNDARY_CHALLINEY_2
import com.gdghackathon.monthlychallenges.NUM_OF_MISSIONS
import com.gdghackathon.monthlychallenges.R

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

@BindingAdapter("set_image")
fun ImageView.setImage(count: Int?) {
    count?.let {
        val resId = if (count <= BOUNDARY_CHALLINEY_1) R.drawable.challiney_1
        else if (count <= BOUNDARY_CHALLINEY_2) R.drawable.challiney_2
        else R.drawable.challiney_3

        setImageDrawable(ResourcesCompat.getDrawable(context.resources, resId, null))
    }
}