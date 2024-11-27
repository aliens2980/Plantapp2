package com.example.plantapp2.beneficial

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
import com.example.plantapp2.data.localData.LocalPlant
import com.example.plantapp2.plants.favorites.FavoritePlantCard
import com.example.plantapp2.plants.favorites.FavoritePlantsViewModel

@Composable
fun BeneficialPlantsScroll(viewModel: FavoritePlantsViewModel) {
    val favoritePlants by viewModel.favoritePlants.observeAsState(emptyList())
    var beneficialPlants = getBeneficialPlants(favoritePlants)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        if (beneficialPlants.isEmpty()) {
            Text(
                text = "No favorite plants yet. Add plants to your favorites to see them here!",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(beneficialPlants) { plant ->
                    BeneficialPlantCard(plant)
                }
            }
        }
    }
}

@Composable
fun BeneficialPlantsScreen(context: Context) {
    val viewModel = FavoritePlantsViewModel(context)
    BeneficialPlantsScroll(viewModel = viewModel)
}

fun getBeneficialPlants (listOfPlants: List<LocalPlant>): List<String> {
    var beneficialPlants = mutableListOf<String>()
    listOfPlants.forEach{ plant ->
        plant.priority.forEach{ list ->
            beneficialPlants = (beneficialPlants + list).toMutableList()
        }
    }
    beneficialPlants = beneficialPlants.distinct().toMutableList()

    return beneficialPlants
}

