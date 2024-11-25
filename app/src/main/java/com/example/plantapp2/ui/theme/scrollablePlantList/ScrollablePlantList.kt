package com.example.plantapp2.ui.theme.scrollablePlantList
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.plants.PlantsViewModel


@Composable
    fun ScrollablePlantList(modifier: Modifier = Modifier, navController: NavController, viewModel: PlantsViewModel = viewModel()) {
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
                    PlantList(
                        plantList = response!!.plants,
                        navController = navController,
                        modifier = Modifier.fillMaxSize() // Ensure it fills the screen
                    )


                }
            }
/*
            else -> {
                // Handle case where the response is valid but the list is empty
                Text("No plants available.")
            }
 */
        }