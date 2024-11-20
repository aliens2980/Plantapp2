package com.example.plantapp2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantapp2.Data.generateSamplePlants

@Composable
fun FavoritePlantScrollView() {
    val plants = generateSamplePlants()

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
fun FavoritePlantScrollViewPreview() {
    FavoritePlantScrollView()
}