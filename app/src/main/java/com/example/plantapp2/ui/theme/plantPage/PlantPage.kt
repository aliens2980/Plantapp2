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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle


@Composable
fun PlantInfoPage(
    navController: NavController,
    modifier: Modifier = Modifier,
    getPlantName: String?,
    viewModel: PlantsViewModel = viewModel()
) {
    var plant by remember { mutableStateOf<Plant?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current


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
                    .padding(horizontal = 25.dp) // Uniform padding for the entire page
            ) {
                Spacer(modifier = Modifier.height(2.dp))

                // Back Button
                Row {
                    BackButton(navController = navController)
                }



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
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GradeImage()
                        InfoSection(title = "Grade", content = it)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Watering Section
                plant?.water?.let {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WaterCanImage()
                        InfoSection(title = "Watering (inches/week)", content = "$it inch(es)")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sunlight Section
                plant?.sun?.let {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SunImage()
                        InfoSection(title = "Sunlight (hours/day)", content = "$it hours")
                    }
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
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
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
