package com.example.plantapp2.navigation.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding


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

    var filterFavorite by remember { mutableStateOf(false) }
    var filterType by remember { mutableStateOf("") }
    var filterColor by remember { mutableStateOf("") }
    var filterHeight by remember { mutableStateOf<Double?>(null) }
    var filterMaxRootNet by remember { mutableStateOf<Double?>(null) }

    val sortedPlants = plants.filter { plant ->
        (!filterFavorite || plant.favorite) &&
                (filterType.isEmpty() || plant.type == filterType) &&
                (filterColor.isEmpty() || plant.color == filterColor) &&
                (filterHeight == null || (filterHeight!! > 0 && plant.height >= filterHeight!!)) &&
                (filterMaxRootNet == null || (filterMaxRootNet!! > 0 && plant.maxRootNet >= filterMaxRootNet!!))
    }
    //var sortedPlants =  plants.filter{it.type == "Grøntsag"}

    //var sortedPlants =  plants.filter{it.type == "Nød"}

    //var sortedPlants =  plants.filter{it.type == "Urt"}

    //var sortedPlants =  plants.filter{it.type == "Frugt"}

    //var sortedPlants =  plants.filter{it.color == "Grøn"}

    //var sortedPlants =  plants.filter{it.color == "Rød"}

    //var sortedPlants =  plants.filter{it.color == "Orange"}

    //var sortedPlants =  plants.filter{it.color == "Brun"}

    //var sortedPlants =  plants.filter{it.height <26.0}

    //var sortedPlants =  plants.filter{it.height >26.0}

    //var sortedPlants =  plants.filter{it.maxRootNet <31.0}

    //var sortedPlants =  plants.filter{it.maxRootNet >31.0}

    //var sortedPlants =  plants.filter{it.favorite}

    //var sortedPlants =  plants.filter{!it.favorite}
    Scaffold { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding).padding(16.dp)) {
            Text("Filter your search results")

            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = filterFavorite, onCheckedChange = { filterFavorite = it })
                Text("Favorite")
            }

            Text("Type:")
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = filterType == "Grøntsag", onCheckedChange = { filterType = if (it) "Grøntsag" else "" })
                Text("Grøntsag")
                Checkbox(checked = filterType == "Nød", onCheckedChange = { filterType = if (it) "Nød" else "" })
                Text("Nød")
                Checkbox(checked = filterType == "Urt", onCheckedChange = { filterType = if (it) "Urt" else "" })
                Text("Urt")
                Checkbox(checked = filterType == "Frugt", onCheckedChange = { filterType = if (it) "Frugt" else "" })
                Text("Frugt")
            }

            Text("Color:")
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = filterColor == "Grøn", onCheckedChange = { filterColor = if (it) "Grøn" else "" })
                Text("Grøn")
                Checkbox(checked = filterColor == "Rød", onCheckedChange = { filterColor = if (it) "Rød" else "" })
                Text("Rød")
                Checkbox(checked = filterColor == "Orange", onCheckedChange = { filterColor = if (it) "Orange" else "" })
                Text("Orange")
                Checkbox(checked = filterColor == "Brun", onCheckedChange = { filterColor = if (it) "Brun" else "" })
                Text("Brun")
            }

            Text("Height:")
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = filterHeight != null && filterHeight!! < 26.0, onCheckedChange = { filterHeight = if (it) 26.0 else null })
                Text("< 26.0 cm")
                Checkbox(checked = filterHeight != null && filterHeight!! > 26.0, onCheckedChange = { filterHeight = if (it) 26.0 else null })
                Text("> 26.0 cm")
            }

            Text("Max Root Net:")
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = filterMaxRootNet != null && filterMaxRootNet!! < 31.0, onCheckedChange = { filterMaxRootNet = if (it) 31.0 else null })
                Text("< 31.0 cm")
                Checkbox(checked = filterMaxRootNet != null && filterMaxRootNet!! > 31.0, onCheckedChange = { filterMaxRootNet = if (it) 31.0 else null })
                Text("> 31.0 cm")
            }

            Column(modifier = Modifier.padding(top = 16.dp)) {
                sortedPlants.forEach { plant ->
                    Text(text = "${plant.name}")
                }
            }
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