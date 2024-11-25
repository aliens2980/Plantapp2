package com.example.plantapp2.ui.theme.scrollablePlantList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.plants.PlantsViewModel
import com.example.plantapp2.ui.theme.filterAndSearch.SearchBarViewModel


@Composable
    fun ScrollablePlantList(modifier: Modifier = Modifier, navController: NavController, viewModel: PlantsViewModel = viewModel()) {
    val response by viewModel.getResponseUsingLiveData().observeAsState()
    val searchText by viewModel.searchText.collectAsState()
    val plants by viewModel.plants.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )
        Spacer(modifier = Modifier.height(16.dp))

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
    }
}
/*
            else -> {
                // Handle case where the response is valid but the list is empty
                Text("No plants available.")
            }
 */
