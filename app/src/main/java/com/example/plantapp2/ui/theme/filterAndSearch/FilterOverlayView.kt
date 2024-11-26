package com.example.plantapp2.ui.theme.filterAndSearch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.plantapp2.ui.theme.plantPage.BackButton
import com.example.plantapp2.ui.theme.styling.darkGreen


@Composable
fun FilterOverlay(
    showOverlay: Boolean,
    onFilterApply: (Int, Int, String) -> Unit,
    onClose: () -> Unit,
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
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = { onClose() },
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            tint = Color.DarkGray,
                            contentDescription = "Close FilterOverlay",
                            modifier = Modifier.size(100.dp),

                            )
                    }
                }
                    var selectedGrade by remember { mutableStateOf("") }
                    Text(
                        "Difficulty:",
                        modifier = Modifier.align(Alignment.Start),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )

                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Checkbox(
                            checked = selectedGrade == "Easy",
                            onCheckedChange = { selectedGrade = if (it) "Easy" else "" },
                            colors = CheckboxDefaults.colors(
                                checkedColor = darkGreen
                            )
                        )
                        Text("Easy")
                        Checkbox(
                            checked = selectedGrade == "Medium",
                            onCheckedChange = { selectedGrade = if (it) "Medium" else "" },
                            colors = CheckboxDefaults.colors(
                                checkedColor = darkGreen
                            )
                        )
                        Text("Medium")
                        Checkbox(
                            checked = selectedGrade == "Hard",
                            onCheckedChange = { selectedGrade = if (it) "Hard" else "" },
                            colors = CheckboxDefaults.colors(
                                checkedColor = darkGreen
                            )
                        )
                        Text("Hard")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

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
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = darkGreen,
                            activeTrackColor = darkGreen,
                        )

                    )

                    Spacer(modifier = Modifier.height(20.dp))

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
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = darkGreen,
                            activeTrackColor = darkGreen,
                        )
                    )

                    // Apply button
                    Button(
                        onClick = {
                            onFilterApply( selectedSun, selectedWater, selectedGrade)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = darkGreen,
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Apply Filters")
                    }
                }
            }
        }
    }