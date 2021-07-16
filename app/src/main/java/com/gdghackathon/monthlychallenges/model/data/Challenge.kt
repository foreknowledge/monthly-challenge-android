package com.gdghackathon.monthlychallenges.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Challenge(
        var id: Long = -1,
        var name: String = "",
        @SerializedName("mission_count")
        var missionCount: Int = 0,
        @SerializedName("create_date")
        var createDate: String = "",
        @SerializedName("ownMissions")
        var missionList: List<Mission> = listOf()
)

data class Mission(
        var id: Long = -1,
        @SerializedName("mission_check")
        var missionCheck: Boolean = false,
        var name: String = "",
        var memo: String? = null,
        @SerializedName("image")
        var imageUrl: String? = null,
        @SerializedName("thumbnail_image")
        var thumbnailImageUrl: String? = null
) : Serializable