package com.example.plantapp2.plants.favorites
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoritePlantsScroll(viewModel: FavoritePlantsViewModel) {
    val favoritePlants by viewModel.favoritePlants.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {

        if (favoritePlants.isEmpty()) {
            Text(
                text = "No favorite plants yet.\nAdd plants to your favorites to see them here!",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(5.dp)
            )
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(favoritePlants) { plant ->
                    FavoritePlantCard(plant)
                }
            }
        }
    }
}

@Composable
fun FavoritePlantsScreen(context: Context) {
    val viewModel = FavoritePlantsViewModel(context)
    FavoritePlantsScroll(viewModel = viewModel)
}







