package com.example.plantapp2.ui.settings.addBed.customizeBed


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BedGrid(
    length: Int,
    width: Int,
    cellState: List<MutableList<Boolean>>,
    onCellClick: (row: Int, col: Int) -> Unit
) {
    // Calculate number of rows and columns based on length and width
    val rows = (length / 20).coerceAtLeast(1)
    val cols = (width / 20).coerceAtLeast(1)

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (row in 0 until rows) {
            Row(horizontalArrangement = Arrangement.Center) {
                for (col in 0 until cols) {
                    Box(
                        modifier = Modifier
                            .size(20.dp) // Each cell is 20x20
                            .background(if (cellState[row][col]) Color.Green else Color.LightGray)
                            .clickable { onCellClick(row, col) }
                            .border(0.5.dp, Color.Black)
                    )
                }
            }
        }
    }
}

