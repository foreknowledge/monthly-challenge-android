package com.gdghackathon.monthlychallenges.utils

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object FileUtil {
    fun createJpgFileExternal(context: Context): File {
        val externalDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return createJpgFile(externalDirectory)
    }

    private fun createJpgFile(directory: File?): File {
        return File.createTempFile(getFilePrefix(), getFileSuffix(), directory)
    }

    private fun getFilePrefix(): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return "JPEG_${timeStamp}_"
    }

    private fun getFileSuffix() = ".jpg"

    private fun deleteFile(filePath: String) = File(filePath).delete()

    fun deleteFiles(filePaths: List<String>) {
        filePaths.forEach { filePath ->
            deleteFile(filePath)
        }
    }
}