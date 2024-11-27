package com.example.plantapp2.beneficial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.plantapp2.data.localData.LocalPlant

@Composable
fun BeneficialPlantCard(plant: LocalPlant) {
    Card(
        modifier = Modifier
            .size(110.dp, 150.dp), // Card size
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Ensures the content fills the card size
                .padding(8.dp), // Padding inside the card
            verticalArrangement = Arrangement.Center, // Centers content vertically
            horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
        ) {
            // Plant Image
            Image(
                painter = rememberAsyncImagePainter(model = plant.image),
                contentDescription = "${plant.name} Image",
                modifier = Modifier
                    .size(100.dp) // Image size
                    .clip(RoundedCornerShape(8.dp)) // Rounded corners for the image
            )
            Spacer(modifier = Modifier.height(6.dp)) // Space between the image and text
            // Plant Name
            Text(
                text = plant.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
