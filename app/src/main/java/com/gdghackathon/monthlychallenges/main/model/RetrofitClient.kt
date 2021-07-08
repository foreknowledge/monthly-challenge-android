package com.gdghackathon.monthlychallenges.main.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = ""
    private val instance: Retrofit? = null

    fun getInstance(): Retrofit =
        instance ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}