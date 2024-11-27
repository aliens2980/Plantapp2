package com.example.plantapp2.data.localData

import kotlinx.serialization.Serializable

@Serializable
data class LocalPlant(
    val id: Int,
    val name: String,
    val isLiked: Boolean,
    val details: String // Add other properties as needed
)
