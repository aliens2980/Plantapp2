package com.example.plantapp2.ui.theme.filterAndSearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
    onFilterApply: (String, Int, String) -> Unit // Lambda to handle filter application
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
                Text("Apply Filters", style = MaterialTheme.typography.bodySmall)

                // Filter by name
                var filterText by remember { mutableStateOf(TextFieldValue("")) }
                BasicTextField(
                    value = filterText,
                    onValueChange = { filterText = it },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    singleLine = true
                )
                Text("Filter by Name: ${filterText.text}")

                // Filter by sun exposure
                var selectedSun by remember { mutableStateOf(0) }
                Text("Filter by Sun Exposure: $selectedSun")
                Slider(
                    value = selectedSun.toFloat(),
                    onValueChange = { selectedSun = it.toInt() },
                    valueRange = 0f..10f,
                    steps = 10,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
                Text(
                    "Type:",
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
                        onFilterApply(filterText.text, selectedSun, selectedGrade)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Apply Filters")
                }
            }
        }
    }
}


