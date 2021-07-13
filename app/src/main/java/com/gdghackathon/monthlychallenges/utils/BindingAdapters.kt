package com.gdghackathon.monthlychallenges.utils

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

@BindingAdapter("set_tintColor")
fun ImageView.setTintColor(missionCheck: Boolean?) {
    missionCheck?.let {
        val tintColorRes = if (it) R.color.color_dark_grey else R.color.color_light_grey
        setColorFilter(ContextCompat.getColor(context, tintColorRes), android.graphics.PorterDuff.Mode.MULTIPLY)
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