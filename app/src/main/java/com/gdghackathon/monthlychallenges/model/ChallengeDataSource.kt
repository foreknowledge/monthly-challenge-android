package com.gdghackathon.monthlychallenges.model

import android.graphics.Bitmap

class ChallengeDataSource {
    private val retrofitService =
        RetrofitClient.getInstance().create(RetrofitService::class.java)

    // 챌린지 생성
    fun createChallenge(name: String, missionList: List<Mission>): Long? =
        retrofitService.createChallenge(name, missionList).execute().body()

    // 챌린지 삭제
    fun deleteChallenge(challengeId: Long): Boolean =
        retrofitService.deleteChallenge(challengeId).execute().body() ?: false

    // 특정 챌린지 조회
    fun getChallenge(challengeId: Long): Challenge? =
        retrofitService.getChallenge(challengeId).execute().body()

    // 챌린지 샘플 조회
    fun getAllSampleChallenges(): List<Challenge>? =
        retrofitService.getAllSampleChallenges().execute().body()

    // 챌린지 미션 조회
    fun getAllMissions(challengeId: Long): List<Mission>? =
        retrofitService.getAllMissions(challengeId).execute().body()

    // 챌린지 미션 인증(사진 업로드)
    fun completeMission(
        challengeId: Long,
        missionId: Long,
        image: Bitmap,
        memo: String?
    ): Mission? =
        retrofitService.completeMission(challengeId, missionId, image, memo).execute().body()
}