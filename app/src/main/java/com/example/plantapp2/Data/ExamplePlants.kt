package com.example.plantapp2.Data

import androidx.compose.runtime.Composable
import com.example.plantapp2.R


@Composable
fun generateSamplePlants(): List<LocalPlant> {
    return listOf(
        LocalPlant(name = "Rose", image = R.drawable.rose, isFavorite = false, size = 2),
        LocalPlant(name = "Tulip", image = R.drawable.tulip, isFavorite = true, size = 1),
        LocalPlant(name = "Sunflower", image = R.drawable.sunflower, isFavorite = false, size = 3),
        LocalPlant(name = "Lavender", image = R.drawable.lavender, isFavorite = false, size = 1),
        LocalPlant(name = "Cactus", image = R.drawable.cactus, isFavorite = true, size = 1),
        LocalPlant(name = "Orchid", image = R.drawable.orchid, isFavorite = false, size = 2)
    )
}

