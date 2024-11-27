package com.example.plantapp2.beneficial

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import coil.compose.AsyncImage
import com.example.plantapp2.data.localData.LocalPlant
import com.example.plantapp2.favorite.getPlantByName
import com.example.plantapp2.plants.favorites.FavoritePlantsViewModel

@Composable
fun BeneficialPlantList (viewModel: FavoritePlantsViewModel) {
    val favoritePlants by viewModel.favoritePlants.observeAsState(emptyList())

    Row (modifier = Modifier.fillMaxWidth().background(Color(0xFF344e41)) //background color
    ) {
        if (result.isEmpty()) {
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
            // Display a list of favorite plants beneficials
            result.forEach { plantName ->
                val plant = getPlantByName(plantName)
                if (plant != null) {
                    Card(
                        modifier = Modifier.padding(10.dp),
                        shape = RoundedCornerShape(12.dp), // Rounded corners for the card
                        colors = CardDefaults.cardColors(Color(0xFFDAD7CD))
                    ) {
                        Column {
                            AsyncImage(
                                model = plant.img, // Ensure this is a valid URL or image resource
                                contentDescription = plant.name,
                                modifier = Modifier
                                    .width(125.dp)
                                    .height(100.dp)
                                , // Ensure consistent image height
                                contentScale = ContentScale.Crop // Crop the image to fit
                            )
                            Text(
                                text = plant.name,
                                modifier = Modifier.padding(10.dp),
                                textAlign = TextAlign.Start, // Align text to the start
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


