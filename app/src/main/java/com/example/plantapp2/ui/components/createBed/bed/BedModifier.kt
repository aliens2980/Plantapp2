package com.example.plantapp2.ui.components.createBed.bed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BedDimensions(viewModel: BedDimensionsViewModel = BedDimensionsViewModel()) {
    val bedLength by viewModel.bedLength.collectAsState()
    val bedWidth by viewModel.bedWidth.collectAsState()
    val cellState by viewModel.cellState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // Input fields for length and width
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TextField(
                value = bedLength.toString(),
                onValueChange = { viewModel.updateBedLength(it.toIntOrNull() ?: 0) },
                placeholder = { Text("Length") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = bedWidth.toString(),
                onValueChange = { viewModel.updateBedWidth(it.toIntOrNull() ?: 0) },
                placeholder = { Text("Width") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }

        // Render grid if valid dimensions
        if (bedLength >= 10 && bedWidth >= 10) {
            BedGrid(
                length = bedLength,
                width = bedWidth,
                cellState = cellState,
                onCellClick = { row, col -> viewModel.toggleCell(row, col) }
            )
        } else {
            Text("Minimum dimensions should be 10x10", color = Color.Red)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BedDimensionsPreview() {
    BedDimensions()
}

