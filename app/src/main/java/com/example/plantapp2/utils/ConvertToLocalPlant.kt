package com.example.plantapp2.utils

import com.example.plantapp2.data.Plant
import com.example.plantapp2.data.localData.LocalPlant

fun Plant.toLocalPlant(): LocalPlant {
    return LocalPlant(
        id = this.hashCode(), // Or another unique identifier
        name = this.name,
        details = this.info,
        isLiked = this.isLiked
    )
}
