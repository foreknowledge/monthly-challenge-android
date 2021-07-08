package com.gdghackathon.monthlychallenges.main.model

import android.graphics.Bitmap
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    // 챌린지 생성
    @POST("challenge")
    fun createChallenge(
        @Field("name") name: String,
        @Field("tag") tag: List<String>
    ): Call<Challenge>

    // 챌린지 삭제
    @DELETE("challenge/{challengeId}")
    fun deleteChallenge(
        @Path("challengeId") challengeId: Long
    ): Call<Challenge>

    // 챌린지 조회 (챌린지 목록, 미션 미포함)
    @GET("challenge/{done}")
    fun getChallenges(): Call<List<Challenge>>

    // 챌린지 샘플 데이터 조회 (미션 포함)
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
        @Field("challengeId") challengeId: Long,
        @Field("missionId") missionId: Long,
        @Part("image") image: Bitmap,
        @Part("memo") memo: String?
    ): Call<Mission>
}