package com.example.plantapp2.data.localData.exampleData

import com.example.plantapp2.data.localData.LocalBeds

fun generateSampleBeds(): List<LocalBeds> {
    return listOf(
        LocalBeds(
            id = 1,
            name = "Example Bed 1",
            length = 200,
            width = 300,
            selectedCells = listOf(0 to 0, 0 to 1, 1 to 0), // Example cell selections
            plants = emptyMap() // No plants

        ),
        LocalBeds(
            id = 2,
            name = "Example Bed 2",
            length = 100,
            width = 50,
            selectedCells = emptyList(), // No cells selected
            plants = emptyMap() // No plants
        ),
        LocalBeds(
            id = 3,
            name = "Example Bed 3",
            length = 240,
            width = 360,
            selectedCells = listOf(0 to 0, 1 to 1),
            plants = emptyMap() // No plants
        )
    )
}
