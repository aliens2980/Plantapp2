package com.example.plantapp2.ui.theme.filterAndSearch

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun FilterOverlay(
    showOverlay: Boolean,
    onFilterApply: (Int, Int, String) -> Unit // Lambda to handle filter application
) {
    if (showOverlay) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .wrapContentSize(align = Alignment.Center)
        ) {
            // Overlay content
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Filter by sun exposure
                var selectedSun by remember { mutableIntStateOf(0) }
                Text(
                "Filter by Sun Exposure: $selectedSun",
                    modifier = Modifier.align(Alignment.Start),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
                Slider(
                    value = selectedSun.toFloat(),
                    onValueChange = { selectedSun = it.toInt() },
                    valueRange = 0f..10f,
                    steps = 10,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )

                var selectedWater by remember { mutableIntStateOf(0) }
                Text(
                    "Filter by Watering Needs: $selectedWater",
                    modifier = Modifier.align(Alignment.Start),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
                Slider(
                    value = selectedWater.toFloat(),
                    onValueChange = { selectedWater = it.toInt() },
                    valueRange = 0f..3f,
                    steps = 3,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
                Text(
                    "Type:",
                    modifier = Modifier.align(Alignment.Start),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
                var selectedGrade by remember { mutableStateOf("") }

                Row (verticalAlignment = Alignment.CenterVertically) {

                    Checkbox(
                        checked = selectedGrade == "Easy",
                        onCheckedChange = { selectedGrade = if (it) "Easy" else "" })
                    Text("Easy")
                    Checkbox(
                        checked = selectedGrade == "Medium",
                        onCheckedChange = { selectedGrade = if (it) "Medium" else "" })
                    Text("Medium")
                    Checkbox(
                        checked = selectedGrade == "Hard",
                        onCheckedChange = { selectedGrade = if (it) "Hard" else "" })
                    Text("Hard")
                }




                // Apply button
                Button(
                    onClick = {
                        onFilterApply( selectedSun, selectedWater, selectedGrade)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Apply Filters")
                }
            }
        }
    }
}


