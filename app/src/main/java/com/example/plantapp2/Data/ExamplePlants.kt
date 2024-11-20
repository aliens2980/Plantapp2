package com.example.plantapp2.Data

import androidx.compose.runtime.Composable
import com.example.plantapp2.R


fun generateSamplePlants(): List<LocalPlant> {
    return listOf(
        LocalPlant(name = "Rose", image = R.drawable.ic_launcher_foreground, isFavorite = false, size = 1, compatiblePlants = listOf("Lavender", "Sunflower")),
        LocalPlant(name = "Tulip", image = R.drawable.ic_launcher_foreground, isFavorite = true, size = 1, compatiblePlants = listOf("Lavender", "Sunflower")),
        LocalPlant(name = "Sunflower", image = R.drawable.ic_launcher_foreground, isFavorite = false, size = 1, compatiblePlants = listOf("Rose", "Tulip")),
        LocalPlant(name = "Lavender", image = R.drawable.ic_launcher_foreground, isFavorite = false, size = 1, compatiblePlants = listOf("Rose", "Tulip")),
        LocalPlant(name = "Cactus", image = R.drawable.ic_launcher_foreground, isFavorite = true, size = 1, compatiblePlants = listOf( "Tulip")),
        LocalPlant(name = "Orchid", image = R.drawable.ic_launcher_foreground, isFavorite = false, size = 1, compatiblePlants = listOf("Tulip", "Sunflower")),
    )
}

