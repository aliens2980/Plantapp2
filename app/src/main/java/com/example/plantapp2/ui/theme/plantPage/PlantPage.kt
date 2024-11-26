package com.example.plantapp2.ui.theme.plantPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel


/**
 * @author s235064
 * @param //PlantPage
 * @return the information page of a plant
 */

@Composable
fun PlantInfoPage(navController: NavController, modifier: Modifier = Modifier, getPlantName: String?, viewModel: PlantsViewModel = viewModel()) {
    var plant by remember { mutableStateOf<Plant?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val plantName = plant?.name
    val imageUrl = plant?.img
    val nameLatin = plant?.nameLatin
    val info = plant?.info
    val water = plant?.water
    val sun = plant?.sun
    val gradeText = plant?.gradeText

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
            Text("Error: $errorMessage", modifier = Modifier.padding(16.dp))
        }

        plant == null -> {
            Text("Loading...", modifier = Modifier.padding(16.dp))
        }

        else -> {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 25.dp) // Uniform padding for the entire page
            ) {
                Spacer(modifier = Modifier.height(2.dp))

                Row{
                    BackButton(navController = navController)
                }

                Box (
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ){ imageUrl?.let {
                    PlantImage(
                        url = it
                    )
                } }
                // Plant Image


                Spacer(modifier = Modifier.height(5.dp))

                // Plant Name and Latin Name
                plantName.let { plantName ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                if (plantName != null) {
                                    PageTitle(name = plantName)
                                }
                                nameLatin?.let { latinName ->
                                    PageTitleLatin(
                                        nameLatin = latinName
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),

                                horizontalAlignment = Alignment.End
                            ) {
                                LikeImage()

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                ) {
                    // Info Section
                    info?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column {
                                Text(
                                    text = "Info",
                                    modifier = Modifier,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.DarkGray
                                    )
                                )

                                InfoText(information = it)
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    //Grading Section
                    gradeText?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            GradeImage()
                            Spacer(modifier = Modifier.width(8.dp))
                            GradeText(information = it)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Watering Section
                    water?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            WaterCanImage()
                            Spacer(modifier = Modifier.width(8.dp))
                            WaterCanText(information = it)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Sunlight Section
                    sun?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            SunImage()
                            Spacer(modifier = Modifier.width(8.dp))
                            SunText(information = it)
                        }
                    }

                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

