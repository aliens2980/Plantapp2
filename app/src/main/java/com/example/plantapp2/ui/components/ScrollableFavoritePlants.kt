package com.example.plantapp2.ui.components

import androidx.compose.runtime.Composable
import com.example.plantapp2.Models.Plant

@Composable
fun FavoritePlantScrollView(plants: List<Plant>) {
    val favoritePlants = plants.filter { it.isFavorite }
}