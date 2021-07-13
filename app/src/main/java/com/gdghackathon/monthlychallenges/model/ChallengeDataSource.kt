package com.gdghackathon.monthlychallenges.model

import android.graphics.Bitmap
import com.gdghackathon.monthlychallenges.model.data.Challenge
import com.gdghackathon.monthlychallenges.model.data.ChallengeRequest
import com.gdghackathon.monthlychallenges.model.data.Mission

class ChallengeDataSource {
    private val retrofitService =
        RetrofitClient.getInstance().create(RetrofitService::class.java)

    // 챌린지 생성
    suspend fun createChallenge(challengeRequest: ChallengeRequest) =
        retrofitService.createChallenge(challengeRequest)

    // 챌린지 삭제
    suspend fun deleteChallenge(challengeId: Long): Boolean =
        retrofitService.deleteChallenge(challengeId)

    // 특정 챌린지 조회
    suspend fun getChallenge(challengeId: Long): Challenge? =
        retrofitService.getChallenge(challengeId)

    // 챌린지 샘플 조회
    suspend fun getAllSampleChallenges(): List<Challenge>? =
        retrofitService.getAllSampleChallenges()

    // 챌린지 미션 조회
    suspend fun getAllMissions(challengeId: Long): List<Mission>? =
        retrofitService.getAllMissions(challengeId)

    // 챌린지 미션 인증(사진 업로드)
    suspend fun completeMission(
        challengeId: Long,
        missionId: Long,
        image: Bitmap,
        memo: String?
    ): Mission? =
        retrofitService.completeMission(challengeId, missionId, image, memo)
}