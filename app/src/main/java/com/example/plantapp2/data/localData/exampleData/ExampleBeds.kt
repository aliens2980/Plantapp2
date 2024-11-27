package com.example.plantapp2.data.localData.exampleData

import com.example.plantapp2.data.localData.LocalBeds
import com.example.plantapp2.data.localData.LocalPlant
import com.example.plantapp2.data.localData.PlaceInBed

fun generateSampleBeds(): List<LocalBeds> {
    val tomatoPlant = LocalPlant(id = 1, name = "Tomato", isLiked = true, details = "Red and juicy")
    val basilPlant = LocalPlant(id = 2, name = "Basil", isLiked = true, details = "Aromatic herb")

    return listOf(
        LocalBeds(
            id = 1,
            name = "Example Bed 1",
            length = 200,
            width = 300,
            selectedCells = listOf(0 to 0, 0 to 1, 1 to 0), // Example cell selections
            plants = mapOf(
                tomatoPlant to PlaceInBed(row = 0, column = 0),
                basilPlant to PlaceInBed(row = 0, column = 1)
            )
        )
    )
}
