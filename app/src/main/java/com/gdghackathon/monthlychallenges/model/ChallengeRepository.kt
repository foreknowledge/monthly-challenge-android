package com.gdghackathon.monthlychallenges.model

import android.graphics.Bitmap

object ChallengeRepository {
    private val challengeDataSource = ChallengeDataSource()

    // 챌린지 생성
    fun createChallenge(name: String, missionList: List<Mission>) =
        challengeDataSource.createChallenge(name, missionList)

    // 챌린지 삭제
    fun deleteChallenge(challengeId: Long) =
        challengeDataSource.deleteChallenge(challengeId)

    // 특정 챌린지 조회
    fun getChallenge(challengeId: Long) =
        challengeDataSource.getChallenge(challengeId)

    // 챌린지 샘플 조회
    fun getAllSampleChallenges() =
        challengeDataSource.getAllSampleChallenges()

    // 챌린지 미션 조회
    fun getAllMissions(challengeId: Long) =
        challengeDataSource.getAllMissions(challengeId)

    // 챌린지 미션 인증(사진 업로드)
    fun completeMission(challengeId: Long, missionId: Long, image: Bitmap, memo: String?) =
        challengeDataSource.completeMission(challengeId, missionId, image, memo)
}