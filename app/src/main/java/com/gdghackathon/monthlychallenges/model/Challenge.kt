package com.gdghackathon.monthlychallenges.model

import com.google.gson.annotations.SerializedName

data class Challenge(
        var id: Long = -1,
        var name: String = "",
        @SerializedName("mission_count")
        var missionCount: Int = 0,
        @SerializedName("create_date")
        var createData: String = "",
        @SerializedName("ownMissions")
        var missionList: List<Mission>? = null
)

data class Mission(
        var id: Long = -1,
        var name: String = "",
        @SerializedName("mission_check")
        var missionCheck: Boolean = false,
        @SerializedName("image")
        var imageUrl: String? = null,
        var memo: String? = null
)