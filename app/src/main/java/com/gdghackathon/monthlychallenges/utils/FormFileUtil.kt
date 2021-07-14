package com.gdghackathon.monthlychallenges.utils

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object FormFileUtil {
    fun getBody(key: String, value: Any): MultipartBody.Part {
        return MultipartBody.Part.createFormData(key, value.toString())
    }

    fun getImageBody(key: String, file: File): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            key,
            file.name,
            RequestBody.create(MediaType.parse("image/*"), file)
        )
    }
}