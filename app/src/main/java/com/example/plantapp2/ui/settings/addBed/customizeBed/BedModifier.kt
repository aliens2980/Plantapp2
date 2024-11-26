package com.example.plantapp2.ui.settings.addBed.customizeBed


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BedDimensions(viewModel: BedDimensionsViewModel) {
    val bedLength by viewModel.bedLength.collectAsState()
    val bedWidth by viewModel.bedWidth.collectAsState()
    val cellState by viewModel.cellState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Debug dimensions
        Text("Length: $bedLength, Width: $bedWidth")

        // Render grid if valid dimensions
        if (bedLength >= 20 && bedWidth >= 20) {
            BedGrid(
                length = bedLength,
                width = bedWidth,
                cellState = cellState,
                onCellClick = { row, col -> viewModel.toggleCell(row, col) },
                onSelectAll = { viewModel.applySelectAll() },
                onCircleFill = { viewModel.applyCircleFill() },
                onLShapeFill = { viewModel.applyLShapeFill() }
            )
        } else {
            Text("Minimum dimensions should be 20x20", color = Color.Red)
        }
    }
}



