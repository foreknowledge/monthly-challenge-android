package com.gdghackathon.monthlychallenges.main.model

data class Challenge(
        var id: Long,
        var name: String,
        var missionCount: Int,
        var tagList: List<String>? = null,
        var missionList: List<Mission>? = null
)

data class Mission(
        var id: Long,
        var name: String,
        var check: Boolean,
        var imageUrl: String? = null,
        var memo: String? = null
)