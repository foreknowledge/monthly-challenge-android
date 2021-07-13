package com.gdghackathon.monthlychallenges.model.data

import com.google.gson.annotations.SerializedName

data class ChallengeRequest(
        val name: String = "",
        @SerializedName("mission")
        val missionList: List<Mission> = listOf()
)