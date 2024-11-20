package com.example.plantapp2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantapp2.Data.LocalPlant


@Composable
fun MainDragPlantComponent(viewModel: MainPlantDragViewModel) {
    val showFavoritePlants by viewModel.showFavoritePlants.observeAsState(true) // Default to true
    val favoritePlants by viewModel.favoritePlants.observeAsState(emptyList())
    val compatiblePlants by viewModel.compatiblePlants.observeAsState(emptyList())

    Column {
        // Toggle Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.togglePlantList(true) },
                colors = ButtonDefaults.buttonColors(
                    if (showFavoritePlants) Color.Blue else Color.Gray
                )
            ) {
                Text("Favorite Plants")
            }
            Button(
                onClick = { viewModel.togglePlantList(false) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!showFavoritePlants) Color.Blue else Color.Gray
                )
            ) {
                Text("Compatible Plants")
            }
        }

        // Scrollable List
        if (showFavoritePlants) {
            FavoritePlantsScroll(favoritePlants)
        } else {
            CompatiblePlantsScroll(compatiblePlants)
        }
    }
}

@Composable
fun FavoritePlantsScroll(plants: List<LocalPlant>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(plants) { plant ->
            FavoritePlantCard(plant)
        }
    }
}

@Composable
fun CompatiblePlantsScroll(plants: List<LocalPlant>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(plants) { plant ->
            FavoritePlantCard(plant)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainDragPlantComponentPreview() {
    MainDragPlantComponent(MainPlantDragViewModel())
}
