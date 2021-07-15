package com.gdghackathon.monthlychallenges.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.FileOutputStream

object BitmapUtil {
    fun bitmapToImageFile(context: Context, bitmap: Bitmap): String {
        val imageFile = FileUtil.createJpgFileExternal(context)
        return compressBitmapToImageFile(imageFile.absolutePath, bitmap)
    }

    private fun compressBitmapToImageFile(imagePath: String, bitmap: Bitmap): String {
        FileOutputStream(imagePath)
            .use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, it)
                it.flush()

                return imagePath
            }
    }
}