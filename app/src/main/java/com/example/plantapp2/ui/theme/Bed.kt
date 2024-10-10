package com.example.plantapp2.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Bed(length: Int, width: Int, gridSize: Int = 30) {
    // Calculate the number of rows and columns based on the given dimensions and grid size
    val numRows = length / gridSize
    val numColumns = width / gridSize

    LazyColumn {
        items(numRows) { rowIndex ->
            LazyRow {
                items(numColumns) { columnIndex ->
                    GridCell(rowIndex, columnIndex, gridSize)
                }
            }
        }
    }
}

@Composable
fun GridCell(row: Int, column: Int, gridSize: Int) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(gridSize.dp)
            .background(if (isSelected) Color.Green else Color.White)
            .clickable {
                // Toggle the selection state on click
                isSelected = !isSelected
            }
    )
}