package com.example.plantapp2.utils

import android.content.Context
import android.util.Log
import com.example.plantapp2.utils.JsonObject.jsonFormat
import java.io.File
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


inline fun <reified T> saveJsonToFile(context: Context, fileName: String, data: T) {
    val directory = File(context.filesDir, "beds")
    if (!directory.exists()) {
        directory.mkdirs()
    }
    val file = File(directory, fileName)
    file.writeText(jsonFormat.encodeToString(data)) // Use shared JsonObject.jsonFormat
    Log.d("SaveJson", "Saved bed to: ${file.absolutePath}")
}
