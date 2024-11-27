package com.example.plantapp2.utils

import android.content.Context
import java.io.File
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun saveJsonToFile(context: Context, fileName: String, data: Any) {
    val directory = File(context.filesDir, "beds")
    if (!directory.exists()) {
        directory.mkdirs()
    }
    val file = File(directory, fileName)
    val json = Json.encodeToString(data)
    file.writeText(json)
}
