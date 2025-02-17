package com.example.plantapp2.ui.theme.scrollablePlantList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
    var sunExposure by rememberSaveable { mutableIntStateOf(0) }
    var grade by rememberSaveable { mutableStateOf("") }
    var waterNeeds by rememberSaveable { mutableIntStateOf(0) }


    // Filtering logic
    val filteredPlants = response?.plants?.filter { plant ->
        val matchesSearch = searchQuery.isBlank() || plant.doesMatchSearchQuery(searchQuery)
        val matchesSunExposure = sunExposure == 0 || plant.sun == sunExposure
        val matchesWaterNeeds = waterNeeds == 0 || plant.water == waterNeeds
        val matchesSelectedGrade = grade.isBlank() || plant.gradeText.equals(grade, ignoreCase = true)
        matchesSearch  && matchesSunExposure && matchesWaterNeeds && matchesSelectedGrade
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
                label = { Text("Search...") },
                modifier = Modifier.weight(1f),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = if(searchQuery.isNotEmpty()) {
                {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        modifier = Modifier.clickable{searchQuery = ""}
                    )
                }} else null,
                singleLine = true,
                shape = RoundedCornerShape(25.dp)
            )
            Column {
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
        if (showFilterOverlay) {
            FilterOverlay(
                showOverlay = true,
                onFilterApply = { sunFilter, waterFilter, gradeFilter ->
                    sunExposure = sunFilter
                    waterNeeds = waterFilter
                    grade = gradeFilter
                    showFilterOverlay = false
                },
                onClose = { showFilterOverlay = false } // Close the overlay when the close button is clicked
            )
        }

        // Plant list or feedback
        Spacer(modifier = Modifier.height(2.dp))
        when {
            response == null -> {
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center)
                    )
                }
            }

            response?.exception != null -> {
                Text("Error: ${response?.exception?.message}", modifier = Modifier.padding(16.dp))
            }

            filteredPlants.isNotEmpty() -> {
                PlantList(
                    plantList = filteredPlants,
                    navController = navController,
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize())
            }

            else -> {
                Text("No results", modifier = Modifier.padding(16.dp))
            }
        }
    }
}