package com.gdghackathon.monthlychallenges.model

import android.graphics.Bitmap

class ChallengeRepository {
    private val challengeDataSource = ChallengeDataSource()

    // 챌린지 생성
    fun createChallenge(name: String, tag: List<String>): Boolean {
        return challengeDataSource.createChallenge(name, tag)
    }

    // 챌린지 삭제
    fun deleteChallenge(challengeId: Long): Boolean {
        return challengeDataSource.deleteChallenge(challengeId)
    }

    // 챌린지 조회(챌린지 목록)
    fun getAllChallenges(): List<Challenge> {
        return challengeDataSource.getAllChallenges()
    }

    // 챌린지 샘플 조회
    fun getAllSampleChallenges(): List<Challenge> {
        return challengeDataSource.getAllSampleChallenges()
    }

    // 챌린지 미션 조회
    fun getAllMissions(challengeId: Long): List<Mission> {
        return challengeDataSource.getAllMissions(challengeId)
    }

    // 챌린지 미션 인증(사진 업로드)
    fun completeMission(challengeId: Long, missionId: Long, image: Bitmap, memo: String?): Boolean {
        return challengeDataSource.completeMission(challengeId, missionId, image, memo)
    }
}