package com.example.plantapp2.plants

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantapp2.data.Plant

@Composable
fun PlantItem(plant: Plant) {
    Column {
        Text("Name: ${plant.name}")
        Text("Latin Name: ${plant.nameLatin}")
        Text("Sunlight: ${plant.sun ?: "Not specified"}")
        Text("Water: ${plant.water ?: "Not specified"}")
        // Add additional details or an image if necessary
    }
}

@Composable
fun PlantsScreen(viewModel: PlantsViewModel = viewModel()) {
    val response by viewModel.getResponseUsingLiveData().observeAsState()

    when {
        response == null -> {
            // Show loading state
            Text("Loading...")
        }
        response?.exception != null -> {
            // Show error state
            Text("Error: ${response?.exception?.message}")
        }
        !response?.plants.isNullOrEmpty() -> {
            // Show plants in a list
            LazyColumn {
                items(response?.plants ?: emptyList()) { plant ->
                    PlantItem(plant)
                }
            }
        }
        else -> {
            // Handle case where the response is valid but the list is empty
            Text("No plants available.")
        }
    }
}
