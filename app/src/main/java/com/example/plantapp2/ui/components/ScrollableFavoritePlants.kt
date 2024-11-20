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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantapp2.Data.LocalPlant
import com.example.plantapp2.Data.generateSamplePlants
/*
@Composable
fun FavoritePlantsScroll(allPlants: List<LocalPlant>) {
    val favoritePlants = allPlants.filter { it.isFavorite } // Filter favorite plants
    if (favoritePlants.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Find your favorite plants in the plant library! When a plant is added as a favorite, it will appear here. Then you can start adding plants to the bed.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = { /* Navigate to plant library */ }) {
                Text("Go to plant library")
            }
        }
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(favoritePlants) { plant ->
                FavoritePlantCard(plant)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FavoritePlantScrollViewPreview() {
    FavoritePlantsScroll(generateSamplePlants())
}

 */