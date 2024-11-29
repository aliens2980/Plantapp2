package com.example.plantapp2.beneficial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.plantapp2.favorite.getPlantByName
import com.example.plantapp2.ui.theme.styling.darkGreen

@Composable
fun BeneficialPlantCard(favPlant: String) {
    val plant = getPlantByName(favPlant)

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(darkGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Ensures the content fills the card size
                .padding(8.dp), // Padding inside the card
            verticalArrangement = Arrangement.Center, // Centers content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
        ) {
            // Plant Image
            if (plant != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = plant.img),
                    contentDescription = "${plant.name} Image",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(8.dp)), // Rounded corners for the image
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(6.dp)) // Space between the image and text
            // Plant Name
            if (plant != null) {
                Text(
                    text = plant.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}
