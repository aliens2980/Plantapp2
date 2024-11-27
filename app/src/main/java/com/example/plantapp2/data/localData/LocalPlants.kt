package com.example.plantapp2.data.localData

import kotlinx.serialization.Serializable


@Serializable
data class LocalPlant(
    val id: Int,
    val name: String,
    val details: String,
    val isLiked: Boolean,
    val image: String,
    val priority: List<String>
)

