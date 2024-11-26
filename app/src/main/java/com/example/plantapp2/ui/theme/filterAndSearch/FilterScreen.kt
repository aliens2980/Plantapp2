package com.example.plantapp2.ui.theme.filterAndSearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/*
@Composable
fun FilterScreen(navController: NavHostController) {

    Scaffold { contentPadding ->
        Row (modifier = Modifier.padding(4.dp)) {
            IconButton(onClick = {navController.popBackStack()},
                modifier = Modifier.padding(0.dp))
            {
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "go back")
            }

            Box( modifier = Modifier.padding(4.dp)){
                Text("Filtrer dine søgeresultater",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp),
                        modifier = Modifier.padding(top = 10.dp) // Adjust this value to change how much lower the text is
                )
            }
        }

        Column(modifier = Modifier.padding(contentPadding).padding(32.dp)) {
            Spacer(modifier = Modifier.height(30.dp)) // Add a spacer to move all items down

            Text("Kun Favoriter:",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = FilterLogic.sortFavorite(), onCheckedChange = { filterFavorite = it })
            }

            Text(
                "Type:",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                        checked = filterType == "Grøntsag",
                        onCheckedChange = { filterType = if (it) "Grøntsag" else "" })
                Text("Grøntsag")
                Checkbox(
                        checked = filterType == "Nød",
                        onCheckedChange = { filterType = if (it) "Nød" else "" })
                Text("Nød")
                Checkbox(
                        checked = filterType == "Urt",
                        onCheckedChange = { filterType = if (it) "Urt" else "" })
                Text("Urt")
                Checkbox(
                        checked = filterType == "Frugt",
                        onCheckedChange = { filterType = if (it) "Frugt" else "" })
                Text("Frugt")
            }

            Text("Farve:",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                        checked = filterColor == "Grøn",
                        onCheckedChange = { filterColor = if (it) "Grøn" else "" })
                Text("Grøn")
                Checkbox(
                        checked = filterColor == "Rød",
                        onCheckedChange = { filterColor = if (it) "Rød" else "" })
                Text("Rød")
                Checkbox(
                        checked = filterColor == "Orange",
                        onCheckedChange = { filterColor = if (it) "Orange" else "" })
                Text("Orange")
                Checkbox(
                        checked = filterColor == "Brun",
                        onCheckedChange = { filterColor = if (it) "Brun" else "" })
                Text("Brun")
            }

            Text(
                "Højde:",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = filterminHeight != null && filterminHeight!! < 26.0,
                    onCheckedChange = {
                        filterminHeight = if (filterminHeight != null && filterminHeight!! < 26.0) {
                            null
                        } else if (it) {
                            25.9
                        } else {
                            null
                        }
                    }
                )
                Text("< 25.0 cm")
                Checkbox(
                    checked = filtermaxHeight != null && filtermaxHeight!! > 26.0,
                    onCheckedChange = {
                        filtermaxHeight = if (filtermaxHeight != null && filtermaxHeight!! > 26.0) {
                            null
                        } else if (it) {
                            26.1
                        } else {
                            null
                        }
                    }
                )
                Text("> 25.0 cm")
            }


            Text(
                "Max Rodnet:",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = filterminMaxRootNet != null && filterminMaxRootNet!! < 31.0,
                    onCheckedChange = {
                        filterminMaxRootNet = if (filterminMaxRootNet != null && filterminMaxRootNet!! < 31.0) {
                            null
                        } else if (it) {
                            30.9
                        } else {
                            null
                        }
                    }
                )
                Text("< 30.0 cm")
                Checkbox(
                    checked = filterMaxRootNet != null && filterMaxRootNet!! >= 31.0,
                    onCheckedChange = {
                        filterMaxRootNet = if (filterMaxRootNet != null && filterMaxRootNet!! >= 31.0) {
                            null
                        } else if (it) {
                            31.0
                        } else {
                            null
                        }
                    }
                )
                Text("≥ 30.0 cm")
            }


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)) // Light green color

            ) {
                Column(modifier = Modifier.padding(top = 0.dp)) {
                    sortedPlants.forEach { plant ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Spacer(modifier = Modifier.height(30.dp)) // Add a spacer to move all items down

                            Text(text = "- ${plant.name} ${plant.emoji}")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FilterScreenPreview (){
    FilterScreen(navController = rememberNavController())
}*/