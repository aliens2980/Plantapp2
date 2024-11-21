package com.example.plantapp2.ui.components.bedChooser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantapp2.Data.Bed


@Composable
fun BedCard(bed: Bed) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Ensure the Column takes up the full size of the Card
                .padding(8.dp), // Padding inside the Card
            verticalArrangement = Arrangement.Center, // Center contents vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center contents horizontally
        ) {

            // Spacer to add vertical spacing between image and text
            Spacer(modifier = Modifier.height(8.dp))
            // Bed Name
            Text(
                text = bed.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
