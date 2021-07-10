package com.gdghackathon.monthlychallenges.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://15.165.229.191:8080/"
    private val instance: Retrofit? = null

    fun getInstance(): Retrofit =
        instance ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}