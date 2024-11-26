package com.example.plantapp2.ui.theme.scrollablePlantList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel

@Composable
fun ScrollablePlantList(modifier: Modifier = Modifier,
                        navController: NavController,
                        viewModel: PlantsViewModel = viewModel()
) {
    val response by viewModel.getResponseUsingLiveData().observeAsState()

    var searchQuery by rememberSaveable { mutableStateOf("") }

    val filteredPlants = response?.plants?.filter {
        it.doesMatchSearchQuery(searchQuery)
    } ?: emptyList()

    Column(modifier = modifier) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search for plants") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))


        when {
            response == null -> {
                // Show loading state
                Text("Loading...", modifier = Modifier.padding(16.dp))
            }

            response?.exception != null -> {
                // Show error state
                Text("Error: ${response?.exception?.message}", modifier = Modifier.padding(16.dp))
            }

            filteredPlants.isNotEmpty() -> {
                    PlantList(
                        plantList = filteredPlants,
                        navController = navController,
                        modifier = Modifier.fillMaxSize())
            }

            else -> {
                // Show message if no plants are found
                Text("No plants found", modifier = Modifier.padding(16.dp))
            }
        }
    }
}


/*
@Composable
    fun ScrollablePlantList(modifier: Modifier = Modifier, navController: NavController, viewModel: PlantsViewModel = viewModel()) {
    val response by viewModel.getResponseUsingLiveData().observeAsState()

    /*val searchText by viewModel.searchText.collectAsState()
    val plants by viewModel.plants.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()*/

    var searchQuery by rememberSaveable { mutableStateOf("") }

    // Filter the plant list based on the search query
    val filteredPlants = response?.plants?.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = modifier) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Plants") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        // Show "No results" text if filtered list is empty
        if (filteredPlants != null) {
            if (filteredPlants.isEmpty()) {
                Text("No plants found", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn {
                    items(filteredPlants) { plant ->
                        PlantCard(plant = plant, navController = navController)
                    }
                }
            }
        }
    }

 */

/*
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
*/ /*
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


fun items(count: List<Plant>, itemContent: LazyItemScope.(index: Int) -> Unit) {

}
/*
            else -> {
                // Handle case where the response is valid but the list is empty
                Text("No plants available.")
            }
 */
*/