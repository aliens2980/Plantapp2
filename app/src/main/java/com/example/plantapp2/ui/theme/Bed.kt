package com.example.plantapp2.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Bed(length: Int, width: Int, gridSize: Int = 60, viewModel: GridViewModel = viewModel()) {
    // Calculate the number of rows and columns
    val numRows = length / gridSize
    val numColumns = width / gridSize

    // Initialize the grid in the ViewModel
    viewModel.initializeGrid(numRows, numColumns)

    Box(
        modifier = Modifier
            .size(width.dp, length.dp)
            .background(color = brown)
    ) {
        // Draw the grid lines
        DrawGridLines(gridSize = gridSize)

        // Overlay interactive grid cells
        DrawGridCells(numRows = numRows, numColumns = numColumns, gridSize = gridSize, viewModel = viewModel)
    }
}

@Composable
fun DrawGridLines(gridSize: Int) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawGridLines(gridSize)
    }
}

@Composable
fun DrawGridCells(numRows: Int, numColumns: Int, gridSize: Int, viewModel: GridViewModel) {
    LazyColumn {
        items(numRows) { rowIndex ->
            LazyRow {
                items(numColumns) { columnIndex ->
                    // Get the clicked state from the ViewModel
                    val isSelected = viewModel.gridState[rowIndex][columnIndex]

                    GridCell(row = rowIndex, column = columnIndex, gridSize = gridSize, isSelected = isSelected) {
                        // Toggle the state in the ViewModel when the cell is clicked
                        viewModel.toggleCell(rowIndex, columnIndex)
                    }
                }
            }
        }
    }
}

@Composable
fun GridCell(row: Int, column: Int, gridSize: Int, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(gridSize.dp)
            .background(if (isSelected) Color.Green else Color.Transparent)
            .clickable {
                onClick() // Call the onClick callback to toggle state
            }
    )
}

fun DrawScope.drawGridLines(gridSize: Int) {
    val step = gridSize.dp.toPx() // Convert grid size from dp to px
    val width = size.width
    val height = size.height

    // Draw vertical lines
    for (x in 0..width.toInt() step step.toInt()) {
        drawLine(
            color = Color.White,
            start = androidx.compose.ui.geometry.Offset(x.toFloat(), 0f),
            end = androidx.compose.ui.geometry.Offset(x.toFloat(), height)
        )
    }

    // Draw horizontal lines
    for (y in 0..height.toInt() step step.toInt()) {
        drawLine(
            color = Color.White,
            start = androidx.compose.ui.geometry.Offset(0f, y.toFloat()),
            end = androidx.compose.ui.geometry.Offset(width, y.toFloat())
        )
    }
}
