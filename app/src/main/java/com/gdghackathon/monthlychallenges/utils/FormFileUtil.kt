package com.gdghackathon.monthlychallenges.utils

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object FormFileUtil {
    fun getBody(key: String, value: Any): MultipartBody.Part {
        return MultipartBody.Part.createFormData(key, value.toString())
    }

    fun getImageBody(key: String, file: File): MultipartBody.Part {
        val filePath = file.absolutePath
        val fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1)
        Log.d("test", "file extension = $fileExtension")
        return MultipartBody.Part.createFormData(
            key,
            file.name,
            file.asRequestBody("image/$fileExtension".toMediaTypeOrNull())
        )
    }
}