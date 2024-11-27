package com.example.plantapp2.data.localData

import kotlinx.serialization.Serializable

@Serializable
data class LocalPlant(
    val id: Int,
    val name: String,
    val details: String, // Add specific fields needed for local storage
    val isLiked: Boolean = true
)

