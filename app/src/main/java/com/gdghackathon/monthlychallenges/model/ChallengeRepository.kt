package com.gdghackathon.monthlychallenges.model

import com.gdghackathon.monthlychallenges.model.data.ChallengeRequest
import okhttp3.MultipartBody

object ChallengeRepository {
    private val challengeDataSource = ChallengeDataSource()

    // 챌린지 생성
    suspend fun createChallenge(challengeRequest: ChallengeRequest) =
        challengeDataSource.createChallenge(challengeRequest)

    // 챌린지 삭제
    suspend fun deleteChallenge(challengeId: Long) =
        challengeDataSource.deleteChallenge(challengeId)

    // 특정 챌린지 조회
    suspend fun getChallenge(challengeId: Long) =
        challengeDataSource.getChallenge(challengeId)

    // 챌린지 샘플 조회
    suspend fun getAllSampleChallenges() =
        challengeDataSource.getAllSampleChallenges()

    // 챌린지 미션 조회
    suspend fun getAllMissions(challengeId: Long) =
        challengeDataSource.getAllMissions(challengeId)

    // 챌린지 미션 인증(사진 업로드)
    suspend fun completeMission(challengeId: Long, missionId: Long, imageFile: MultipartBody.Part?, memo: MultipartBody.Part) =
        challengeDataSource.completeMission(challengeId, missionId, imageFile, memo)
}