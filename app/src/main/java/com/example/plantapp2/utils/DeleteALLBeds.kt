package com.example.plantapp2.utils

import android.content.Context
import android.util.Log
import java.io.File

fun deleteAllFiles(context: Context) {
    val directory = File(context.filesDir, "beds")
    if (directory.exists() && directory.isDirectory) {
        directory.listFiles()?.forEach { it.delete() }
        Log.d("FileDeletion", "All files in 'beds' directory deleted")
    } else {
        Log.d("FileDeletion", "Directory does not exist or is not a directory")
    }
}
