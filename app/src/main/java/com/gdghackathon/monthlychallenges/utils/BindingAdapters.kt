package com.gdghackathon.monthlychallenges.utils

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gdghackathon.monthlychallenges.BOUNDARY_CHALLINEY_1
import com.gdghackathon.monthlychallenges.BOUNDARY_CHALLINEY_2
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.model.data.Challenge

@BindingAdapter("set_startDate")
fun TextView.setStartDate(date: String?) {
    date?.let {
        date.substringBefore("T")
        val startDate = "${date.substringBefore("T")} ~"
        text = startDate
    }
}

@BindingAdapter("set_missionCount")
fun TextView.setMissionCount(challenge: Challenge?) {
    challenge?.let {
        val missionCount = "${it.missionCount}/${it.missionList.size}"
        text = missionCount
    }
}

@BindingAdapter("change_image")
fun ImageView.changeImage(count: Int?) {
    count?.let {
        val resId = when {
            it <= BOUNDARY_CHALLINEY_1 -> R.drawable.challiney_1
            it <= BOUNDARY_CHALLINEY_2 -> R.drawable.challiney_2
            else -> R.drawable.challiney_3
        }

        setImageDrawable(ResourcesCompat.getDrawable(context.resources, resId, null))
    }
}

@BindingAdapter("set_backgroundColor")
fun ImageView.setBackgroundColor(missionCheck: Boolean?) {
    missionCheck?.let {
        Log.d("test", "m = $missionCheck")
        val tintColorRes = if (it) R.color.color_dark_grey else R.color.color_light_grey
        setBackgroundColor(ContextCompat.getColor(context, tintColorRes))
    }
}

@BindingAdapter("set_image")
fun ImageView.setImage(url: String?) {
    url?.let {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("set_textColor")
fun TextView.setTextColor(missionCheck: Boolean?) {
    missionCheck?.let {
        val colorRes = if (it) R.color.white else R.color.color_dark_grey
        setTextColor(context.getColor(colorRes))
    }
}