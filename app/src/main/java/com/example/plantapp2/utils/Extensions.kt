package com.example.plantapp2.utils

import com.example.plantapp2.data.Plant
import com.example.plantapp2.data.localData.LocalPlant

fun Plant.toLocalPlant(): LocalPlant {
    return LocalPlant(
        id = this.hashCode(), // Or another unique identifier
        name = this.name,
        details = this.info,
        isLiked = this.isLiked,
        image = this.img,
        priority = this.prio
    )
}

fun LocalPlant.toPlant(): Plant {
    return Plant(
        name = this.name,
        info = this.details,
        img = this.image,
        isLiked = this.isLiked,
        prio = this.priority
    )
}
