package com.example.plantapp2.Data

import androidx.compose.runtime.Composable
import com.example.plantapp2.R


@Composable
fun generateSamplePlants(): List<LocalPlant> {
    return listOf(
        LocalPlant(name = "Rose", image = R.drawable.ic_launcher_foreground, isFavorite = false, size = 1),
        LocalPlant(name = "Tulip", image = R.drawable.ic_launcher_foreground, isFavorite = true, size = 1),
        LocalPlant(name = "Sunflower", image = R.drawable.ic_launcher_foreground, isFavorite = false, size = 1),
        LocalPlant(name = "Lavender", image = R.drawable.ic_launcher_foreground, isFavorite = false, size = 1),
        LocalPlant(name = "Cactus", image = R.drawable.ic_launcher_foreground, isFavorite = true, size = 1),
        LocalPlant(name = "Orchid", image = R.drawable.ic_launcher_foreground, isFavorite = false, size = 1)
    )
}

