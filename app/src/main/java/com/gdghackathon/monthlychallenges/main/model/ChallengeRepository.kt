package com.gdghackathon.monthlychallenges.main.model

import android.graphics.Bitmap

class ChallengeRepository {
    private val challengeDataSource: ChallengeDataSource = ChallengeDataSource()

    fun createChallenge(name: String, tag: List<String>): Boolean { TODO("챌린지 생성") }
    fun deleteChallenge(challengeId: Long): Boolean { TODO("챌린지 삭제") }
    fun getAllChallenges(): List<Challenge> { TODO("챌린지 조회(챌린지 목록)") }
    fun getAllSampleChallenges(): List<Challenge> { TODO("챌린지 샘플 조회") }
    fun getAllMissions(challengeId: Long): List<Mission> { TODO("챌린지 미션 조회") }
    fun completeMission(challengeId: Long, missionId: Long, image: Bitmap, memo: String?): Boolean { TODO("챌린지 미션 인증(사진 업로드)") }
}