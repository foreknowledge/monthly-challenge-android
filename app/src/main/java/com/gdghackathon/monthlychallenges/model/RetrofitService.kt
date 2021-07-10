package com.gdghackathon.monthlychallenges.model

import android.graphics.Bitmap
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    // 챌린지 생성
    @FormUrlEncoded
    @POST("challenge")
    fun createChallenge(
        @Field("name") name: String,
        @Field("mission") missionList: List<Mission>
    ): Call<Long>

    // 챌린지 삭제
    @DELETE("challenge/{challengeId}")
    fun deleteChallenge(
        @Path("challengeId") challengeId: Long
    ): Call<Challenge>

    // 특정 챌린지 조회 (미션 포함)
    @GET("challenge/{challengeId}")
    fun getChallenge(
        @Path("challengeId") challengeId: Long
    ): Call<Challenge>

    // 챌린지 샘플 데이터 조회 (8개, 미션 포함)
    @GET("challenge/samples")
    fun getAllSampleChallenges(): Call<List<Challenge>>

    // 챌린지 미션 조회 (전체 미션)
    @GET("challenge/{challengeId}/mission")
    fun getAllMissions(
        @Path("challengeId") challengeId: Long
    ): Call<List<Mission>>

    // 챌린지 미션 인증 (챌린지 미션에 사진 올리기)
    @Multipart
    @POST("challenge/{challengeId}/mission/{missionId}")
    fun completeMission(
        @Path("challengeId") challengeId: Long,
        @Path("missionId") missionId: Long,
        @Part("image") image: Bitmap,
        @Part("memo") memo: String?
    ): Call<Mission>
}