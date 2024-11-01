package com.example.plantapp2.navigation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SideRegPlants() {
    Scaffold(
        content = { padding -> // Content lambda with padding parameter
            // Apply the modifier to your content here
            Box(modifier = Modifier
                .padding(padding) // Apply Scaffold's padding
                .fillMaxHeight()
                .width(20.dp)
            ) {
                // Your content inside the Box
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SideRegPlantsPreview() {
    SideRegPlants()
}

