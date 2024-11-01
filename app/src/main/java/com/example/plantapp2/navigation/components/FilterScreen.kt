package com.example.plantapp2.navigation.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@Composable
fun FilterScreen(navController: NavHostController) {

    val plants = remember {
        mutableStateListOf(
            Plant("Kartofler", "Grøntsag", "Brun", 10.0, 20.0, true),
            Plant("Jordbær", "Nød", "Rød", 25.0, 10.0, false),
            Plant("Brændnæller", "Urt", "Grøn", 30.0, 30.0, true),
            Plant("Gulerødder", "Grøntsag", "Orange", 10.0, 40.0, false),
            Plant("Oliven", "Frugt", "Grøn", 100.0, 40.0, true),
            Plant("Tomato", "Grøntsag", "Rød", 80.0, 50.0, true),
            Plant("Agurk", "Grøntsag", "Grøn", 25.0, 30.0, false)
        )
    }
    var sortedPlants =  plants.filter{it.type == "Grøntsag"}

    Column(modifier = Modifier.padding(16.dp)) {
        sortedPlants.forEach { plant ->
            Text(text = "${plant.name} - ${plant.type}")
        }
    }

    
}

data class Plant(
    val name: String,
    val type: String,
    val color: String,
    val height: Double,
    val maxRootNet: Double,
    val favorite: Boolean,
)

@Preview(showBackground = true)
@Composable
fun FilterScreenPreview (){
    FilterScreen(navController = rememberNavController())
}