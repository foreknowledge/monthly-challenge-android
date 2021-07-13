package com.gdghackathon.monthlychallenges.model

import android.graphics.Bitmap
import com.gdghackathon.monthlychallenges.model.data.Challenge
import com.gdghackathon.monthlychallenges.model.data.ChallengeRequest
import com.gdghackathon.monthlychallenges.model.data.ChallengeResponse
import com.gdghackathon.monthlychallenges.model.data.Mission
import retrofit2.http.*

interface RetrofitService {
    // 챌린지 생성
    @POST("challenge")
    suspend fun createChallenge(
        @Body challengeRequest: ChallengeRequest
    ): ChallengeResponse

    // 챌린지 삭제
    @DELETE("challenge/{challengeId}")
    suspend fun deleteChallenge(
        @Path("challengeId") challengeId: Long
    )

    // 특정 챌린지 조회 (미션 포함)
    @GET("challenge/{challengeId}")
    suspend fun getChallenge(
        @Path("challengeId") challengeId: Long
    ): Challenge

    // 챌린지 샘플 데이터 조회 (8개, 미션 포함)
    @GET("challenge/samples")
    suspend fun getAllSampleChallenges(): List<Challenge>

    // 챌린지 미션 조회 (전체 미션)
    @GET("challenge/{challengeId}/mission")
    suspend fun getAllMissions(
        @Path("challengeId") challengeId: Long
    ): List<Mission>

    // 챌린지 미션 인증 (챌린지 미션에 사진 올리기)
    @Multipart
    @POST("challenge/{challengeId}/mission/{missionId}")
    suspend fun completeMission(
        @Path("challengeId") challengeId: Long,
        @Path("missionId") missionId: Long,
        @Part("image") image: Bitmap,
        @Part("memo") memo: String?
    ): Mission
}