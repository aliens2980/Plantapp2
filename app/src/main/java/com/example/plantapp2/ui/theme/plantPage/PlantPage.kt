package com.example.plantapp2.ui.theme.plantPage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel

@Composable
fun PlantInfoPage(
    navController: NavController,
    modifier: Modifier = Modifier,
    getPlantName: String?,
    viewModel: PlantsViewModel = viewModel()
) {
    var plant by remember { mutableStateOf<Plant?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(getPlantName) {
        if (getPlantName != null) {
            try {
                plant = viewModel.getPlantDetailsByName(getPlantName)
            } catch (e: Exception) {
                errorMessage = "Error fetching plant details: ${e.message}"
            }
        }
    }

    when {
        errorMessage != null -> {
            Text(
                text = "Error: $errorMessage",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        plant == null -> {
            Text(
                text = "Loading...",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Back Button
                Row {
                    BackButton(navController = navController)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Plant Image
                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    plant?.img?.let {
                        PlantImage(url = it)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Plant Name and Latin Name
                plant?.let {
                    PlantDetailsHeader(plant = it, viewModel = viewModel)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Info Section
                plant?.info?.let {
                    InfoSection(title = "Info", content = it)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Grading Section
                plant?.gradeText?.let {
                    InfoSection(title = "Grade", content = it)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Watering Section
                plant?.water?.let {
                    InfoSection(title = "Watering (days)", content = "$it days")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sunlight Section
                plant?.sun?.let {
                    InfoSection(title = "Sunlight (hours)", content = "$it hours")
                }
            }
        }
    }
}

@Composable
fun PlantDetailsHeader(plant: Plant, viewModel: PlantsViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = plant.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            plant.nameLatin?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        LikeImage(
            plant = plant,
            viewModel = viewModel,
            modifier = Modifier.size(50.dp)
        )
    }
}


@Composable
fun InfoSection(title: String, content: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
