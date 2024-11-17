package com.example.plantapp2.mvvm.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantapp2.Models.Plant

/*
    This file is for all visual logic. Visual logic entails everything that has to do
    with a visual representation for the user.
 */

@Composable
fun HomeView(
    // Create a viewmodel for controllinga ny business logic in this view
    homeViewModel: HomeViewModel = viewModel()
) {

    // Makes the data available for this view, so that it can display the current state
    // of the model as it currently is.
    val homeUiState by homeViewModel.uiState.collectAsState()

    // Looks inside the uiState's plants list and prints the first element
    if (homeUiState.plants.isNotEmpty()) {
        Text("plant name: " + homeUiState.plants[0])
    }
    else {
        Text("No plants called Jens here...")
    }

    Button(onClick = {
        val plant: Plant = homeViewModel.getPlantJens()
        homeViewModel.addPlantToList(plant)
    }) {
        Text("Add the plant that i someho specified already for some reason")
    }




}