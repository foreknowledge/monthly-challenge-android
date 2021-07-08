package com.gdghackathon.monthlychallenges.main.model

class ChallengeRemote {
    private val retrofitService: RetrofitService =
        RetrofitClient.getInstance().create(RetrofitService::class.java)
}