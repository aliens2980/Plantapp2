package com.example.plantapp2.favorite

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel
import com.example.plantapp2.ui.theme.styling.darkGreen

@Composable
fun FavoritePlantList(context: Context) {
    val sharedPrefs = context.getSharedPreferences("plant_preferences", Context.MODE_PRIVATE)

    // Get all the plant names that are marked as favorites
    val favoritePlants = sharedPrefs.all.filter { it.value as? Boolean == true }.keys
    Row (modifier = Modifier.fillMaxWidth().background(Color(0xFF344e41)) //background color
    ) {
        if (favoritePlants.isEmpty()) {
            Text(
                text = "No favorite plants yet.",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(16.dp)
            )
        } else {
            // Display a list of favorite plants
            favoritePlants.forEach { plantName ->
                val plant = getPlantByName(plantName)
                if (plant != null) {
                    Card(
                        modifier = Modifier.padding(10.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(darkGreen)
                    ) {
                        Column {
                            AsyncImage(
                                model = plant.img,
                                contentDescription = plant.name,
                                modifier = Modifier
                                    .width(125.dp)
                                    .height(100.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = plant.name,
                                modifier = Modifier.padding(10.dp),
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Default,
                                    color = Color.DarkGray
                                )
                            )
                        }
                    }
                } else {
                    Text("Plant is not available")
                }
            }
        }
    }
}
/*
@Composable
fun getPlantByName(plantName: String, viewModel: PlantsViewModel = viewModel()
): Plant? {
    val response by viewModel.getResponseUsingLiveData().observeAsState()
    return response?.plants?.find { it.name.equals(plantName, ignoreCase = true) }
}
*/

