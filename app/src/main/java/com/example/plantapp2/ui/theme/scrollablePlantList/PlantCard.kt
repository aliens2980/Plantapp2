package com.example.plantapp2.ui.theme.scrollablePlantList

import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.plantapp2.ui.theme.plantPage.LikeImage

@Composable
fun PlantCard(plant: Plant, modifier: Modifier = Modifier, viewModel: PlantsViewModel) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp), // Rounded corners for the card
        colors = CardDefaults.cardColors(Color(0xFFDAD7CD))
    ) {
        Column {
            AsyncImage(
                model = plant.img,
                contentDescription = plant.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp), // Ensure consistent image height
                contentScale = ContentScale.Crop // Crop the image to fit
            )
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = plant.name,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Start, // Align text to the star
                    style = TextStyle (
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        color = Color.DarkGray
                    )
                )
                Spacer(Modifier.weight(1f))

                LikeImage(plant = plant, viewModel = viewModel) }

        }
    }
}