package com.example.plantapp2.ui.theme.scrollablePlantList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel
import com.example.plantapp2.ui.theme.filterAndSearch.FilterOverlay

@Composable
fun ScrollablePlantList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PlantsViewModel = viewModel()
) {
    val response by viewModel.getResponseUsingLiveData().observeAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var showFilterOverlay by remember { mutableStateOf(false) }

    // States for applied filters
    var filterText by rememberSaveable { mutableStateOf("") }
    var sunExposure by rememberSaveable { mutableStateOf(0) }
    var grade by rememberSaveable { mutableStateOf("") }

    // Filtering logic
    val filteredPlants = response?.plants?.filter { plant ->
        val matchesSearch = searchQuery.isBlank() || plant.doesMatchSearchQuery(searchQuery)
        val matchesFilterText = filterText.isBlank() || plant.name.contains(filterText, ignoreCase = true)
        val matchesSunExposure = sunExposure == 0 || plant.sun == sunExposure
        val matchesSelectedGrade = grade.isBlank() || plant.gradeText.equals(grade, ignoreCase = true)
        matchesSearch && matchesFilterText && matchesSunExposure && matchesSelectedGrade
    } ?: response?.plants ?: emptyList() // Show all plants by default if no filters are applied

    Column(modifier = modifier.fillMaxSize()) {
        // Search bar and filter button
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search for plants") },
                modifier = Modifier.weight(1f)
            )
            Column(
            ) {
                IconButton(
                    onClick = {
                        if (showFilterOverlay) {
                            showFilterOverlay = false
                        } else {
                            showFilterOverlay = true
                        }
                    },

                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Open Filters"
                    )

                }
                Text(
                    "Filters",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 15.dp)


                )
            }
        }



        // Filter overlay
        FilterOverlay(
            showOverlay = showFilterOverlay,
            onFilterApply = { nameFilter, sunFilter, gradeFilter ->
                filterText = nameFilter
                sunExposure = sunFilter
                grade = gradeFilter
                showFilterOverlay = false // Close the overlay
            }
        )

        // Plant list or feedback
        Spacer(modifier = Modifier.height(16.dp))
        when {
            response == null -> {
                Text("Loading...", modifier = Modifier.padding(16.dp))
            }

            response?.exception != null -> {
                Text("Error: ${response?.exception?.message}", modifier = Modifier.padding(16.dp))
            }

            filteredPlants.isNotEmpty() -> {
                PlantList(
                    plantList = filteredPlants,
                    navController = navController,
                    modifier = Modifier.fillMaxSize())
            }

            else -> {
                Text("No plants match your filters.", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

/*
package com.example.plantapp2.ui.theme.scrollablePlantList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel
import com.example.plantapp2.ui.theme.filterAndSearch.FilterOverlay

@Composable
fun ScrollablePlantList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PlantsViewModel = viewModel()
) {
    val response by viewModel.getResponseUsingLiveData().observeAsState()

    var searchQuery by rememberSaveable { mutableStateOf("") }
    var showFilterOverlay by remember { mutableStateOf(false) }

    // Filtered plants based on search query and filters
    var filteredPlants = response?.plants?.filter {
        it.doesMatchSearchQuery(searchQuery)
    } ?: emptyList()

    // Apply filters when the user applies them
    fun applyFilters(filterText: String, sunExposure: Int) {
        filteredPlants = response?.plants?.filter {
            it.doesMatchSearchQuery(filterText) && (it.sun == sunExposure)
        } ?: emptyList()
    }

    Column(modifier = modifier) {
        // Search bar and filter icon in a Row
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Search bar (make it smaller by setting width)
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search for plants") },
                modifier = Modifier.width(325.dp) // Set the desired width for the search bar
            )

            Box(
                modifier = Modifier
                    .padding(12.dp) // Add padding inside the box
                    .clickable(onClick = { showFilterOverlay = !showFilterOverlay }) // Toggle overlay visibility
            ) {
                // Filter icon with text
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Filter",
                        modifier = Modifier.padding(bottom = 4.dp) // Space between the icon and text
                    )

                    // Text under the icon
                    Text(
                        text = "Filter",
                        style = MaterialTheme.typography.bodySmall, // Use a small text style
                        modifier = Modifier.padding(top = 4.dp) // Optional padding
                    )
                }
            }
        }

        // Show filter overlay (with filtering functionality)
        FilterOverlay(showOverlay = showFilterOverlay, onFilterApply = ::applyFilters)

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
fun ScrollablePlantList(modifier: Modifier = Modifier,
                        navController: NavController,
                        viewModel: PlantsViewModel = viewModel()
) {
    val response by viewModel.getResponseUsingLiveData().observeAsState()

    var searchQuery by rememberSaveable { mutableStateOf("") }

    val filteredPlants = response?.plants?.filter {
        it.doesMatchSearchQuery(searchQuery)
    } ?: emptyList()
    var showFilterOverlay by remember { mutableStateOf(false) }


    Column(modifier = modifier) {
        // Search bar and filter icon in a Row
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            // Search bar (make it smaller by setting width)
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search for plants") },
                modifier = Modifier.width(325.dp) // Set the desired width for the search bar
            )

            Box(
                modifier = Modifier
                    .clip(RectangleShape) // Makes it rectangular
                    .padding(12.dp) // Add padding inside the box
            ) {
                // Clickable area with filter icon and text
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.clickable(onClick = { showFilterOverlay = !showFilterOverlay })
                ) {
                    // Filter icon
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Filter",
                        modifier = Modifier.padding(bottom = 4.dp) // Space between the icon and text
                    )

                    // Text under the icon
                    Text(
                        text = "Filter",
                        style = MaterialTheme.typography.bodySmall, // Use a small text style
                        modifier = Modifier.padding(top = 4.dp) // Optional padding
                    )
                }
            }
        }
        FilterOverlay(showOverlay = showFilterOverlay)

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


 */


 */