package com.example.plantapp2.utils

import kotlinx.serialization.json.Json

object JsonObject {
    val jsonFormat = Json {
        allowStructuredMapKeys = true
        explicitNulls = false // Avoid serializing unnecessary nulls
    }
}