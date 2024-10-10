package com.example.plantapp2.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Bed(length: Int, width: Int) {
    Box(
        modifier = Modifier
            .size(width.dp, length.dp)
            .background(color = brown)
    ) {
        DrawGrid(gridSize = 30)
    }
}

@Composable
fun DrawGrid(gridSize: Int) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawGrid(gridSize)
    }
}

fun DrawScope.drawGrid(gridSize: Int) {
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