package com.example.plantapp2.utils

import android.content.Context
import java.io.File
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


inline fun <reified T> saveJsonToFile(context: Context, fileName: String, data: T) {
    val directory = File(context.filesDir, "beds")
    if (!directory.exists()) {
        directory.mkdirs()
    }
    val file = File(directory, fileName)

    // Enable structured map keys
    val json = Json { allowStructuredMapKeys = true }
    file.writeText(json.encodeToString(data))
}

