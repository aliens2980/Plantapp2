package com.example.plantapp2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BedCreationInfo() {
    var bedLength by remember { mutableStateOf("") }
    var bedHeight by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = { innerPadding -> // Scaffold provides `innerPadding`
            Text(
                modifier = Modifier
                    .padding(innerPadding) // Apply Scaffold's innerPadding
                    .padding(16.dp), // Additional padding
                text = "Create your virtual bed here. Type the length and width of your bed in cm, below.",
                color = Color.Black,
                fontSize = 16.sp,
                letterSpacing = 0.5.sp, // Example letter spacing
                textDecoration = null, // Default value
                textAlign = TextAlign.Center, // Example text alignment
                lineHeight = 20.sp, // Example line height
                overflow = TextOverflow.Ellipsis, // Example overflow behavior
                softWrap = true, // Wrap text by default
                maxLines = 3, // Limit to 3 lines
                style = MaterialTheme.typography.bodyMedium // Use a style
            )
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(bottom = 40.dp)
                    .fillMaxWidth(), // Ensure the Box fills the width of the screen
                contentAlignment = Alignment.Center // Align content within the Box
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp) // Padding around the row
                        .fillMaxWidth(), // Ensure Row fills available width
                    horizontalArrangement = Arrangement.SpaceEvenly, // Space the items evenly
                    verticalAlignment = Alignment.CenterVertically // Align items vertically in the center
                ) {
                    TextField(
                        value = bedLength,
                        onValueChange = { input ->
                            // Allow only numbers
                            if (input.all { it.isDigit() }) {
                                bedLength = input
                            }
                        },
                        placeholder = {
                            Text(text = "Bed length")
                        },
                        modifier = Modifier
                            .weight(1f) // Distribute space evenly between TextFields
                            .padding(end = 8.dp), // Add spacing between TextFields
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Set numeric keyboard
                    )

                    TextField(
                        value = bedHeight,
                        onValueChange = { input ->
                            // Allow only numbers
                            if (input.all { it.isDigit() }) {
                                bedHeight = input
                            }
                        },
                        placeholder = {
                            Text(text = "Bed length")
                        },
                        modifier = Modifier
                            .weight(1f) // Distribute space evenly between TextFields
                            .padding(end = 8.dp), // Add spacing between TextFields
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Set numeric keyboard
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BedCreationInfoPreview() {
    BedCreationInfo()
}

