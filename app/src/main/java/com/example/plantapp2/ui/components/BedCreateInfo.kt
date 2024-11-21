package com.example.plantapp2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BedCreationInfo(viewModel: BedCreationViewModel) {
    val bedLength by viewModel.bedLength.collectAsState()
    val bedWidth by viewModel.bedWidth.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(
                    text = "Create your virtual bed here. Type the length and width of your bed in cm, below.",
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextField(
                        value = bedLength,
                        onValueChange = { viewModel.updateBedLength(it) },
                        placeholder = { Text(text = "Bed length in cm") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    TextField(
                        value = bedWidth,
                        onValueChange = { viewModel.updateBedWidth(it) },
                        placeholder = { Text(text = "Bed width in cm") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Text(
                    text = "Length: $bedLength cm.    Width: $bedWidth cm",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BedCreationInfoPreview() {
    BedCreationInfo(viewModel = BedCreationViewModel())
}

