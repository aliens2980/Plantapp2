package com.example.plantapp2.utils

import android.content.Context
import java.io.File
import kotlinx.serialization.json.Json

inline fun <reified T> readJsonFromFile(context: Context, fileName: String): T? {
    val file = File(context.filesDir, "beds/$fileName")
    return if (file.exists()) {
        val json = file.readText()
        Json.decodeFromString(json)
    } else {
        null
    }
}
