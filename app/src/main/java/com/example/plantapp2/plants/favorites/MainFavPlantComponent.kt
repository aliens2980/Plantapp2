package com.example.plantapp2.plants.favorites

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainFavoritePlantComponent(context: Context) {
    val viewModel: FavoritePlantsViewModel = viewModel { FavoritePlantsViewModel(context) }
    val favoritePlants by viewModel.favoritePlants.observeAsState(emptyList())

    Column {
        Text("Your Favorite Plants", style = MaterialTheme.typography.headlineSmall)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favoritePlants) { plant ->
                FavoritePlantCard(plant)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainFavoritePlantComponent() {
    // Replace with a mocked context for previewing in IDE
    MainFavoritePlantComponent(context = android.content.ContextWrapper(null))
}
