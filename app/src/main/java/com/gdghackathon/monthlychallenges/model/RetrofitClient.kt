package com.gdghackathon.monthlychallenges.model

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

object RetrofitClient {
    private const val BASE_URL = ""
    private val instance: Retrofit? = null

    fun getInstance(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        return instance ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}

class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val delegate = retrofit!!.nextResponseBodyConverter<Any>(this, type!!, annotations!!)
        return Converter<ResponseBody, Any> {
            if (it.contentLength() == 0L) return@Converter EmptyResponse()
            delegate.convert(it)
        }
    }
}

class EmptyResponse