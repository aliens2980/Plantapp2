package com.example.plantapp2.favorite

import android.content.Context
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.plantapp2.data.Response
import com.example.plantapp2.plants.PlantsViewModel
import com.example.plantapp2.ui.theme.plantPage.LikeImage
import com.example.plantapp2.ui.theme.styling.darkGreen

@Composable
fun FavoritePlantCard(plantName: String, context: Context) {
    // Fetch the plant details from the plant list or database
    val plant = getPlantByName(plantName)

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(12.dp), // Rounded corners for the card
        colors = CardDefaults.cardColors(darkGreen)
    ) {
        Row {
            AsyncImage(
                model = plant?.img,
                contentDescription = plant?.name,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            if (plant != null) {
                Text(
                    text = plant.name,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Start,
                    style = TextStyle (
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        color = Color.DarkGray
                    )
                )
            }
        }
    }
}
@Composable
fun getPlantByName(plantName: String, viewModel: PlantsViewModel = viewModel()
): Plant? {
    val response by viewModel.getResponseUsingLiveData().observeAsState()
    return response?.plants?.find { it.name.equals(plantName, ignoreCase = true) }
}






