package com.example.plantapp2.ui.theme.scrollablePlantList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.plantapp2.data.Plant

@Composable
fun PlantList(
    plantList: List<Plant>?,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Default to empty list if plantList is null
    val nonNullPlantList = plantList ?: emptyList()

    // Use LazyColumn for vertical scrolling
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ensures list occupies full available space
            .padding(vertical = 8.dp) // Adds vertical padding for aesthetics
            .background(Color(0xFF344e41)) //background color
    ) {
        items(nonNullPlantList) { plant ->
            PlantCard(
                plant = plant,
                modifier = Modifier
                    .fillMaxWidth() // Ensure card spans the full width
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("plantPage/${plant.name}")
                    }
            )
        }
    }
}




