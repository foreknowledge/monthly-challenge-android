package com.gdghackathon.monthlychallenges.model

import com.google.gson.annotations.SerializedName

data class Challenge(
        var id: Long,
        var name: String,
        @SerializedName("missioncount")
        var missionCount: Int,
        @SerializedName("tag")
        var tagList: List<String>? = null,
        @SerializedName("mission")
        var missionList: List<Mission>? = null
)

data class Mission(
        var id: Long,
        var name: String,
        var check: Boolean,
        @SerializedName("image")
        var imageUrl: String? = null,
        var memo: String? = null
)