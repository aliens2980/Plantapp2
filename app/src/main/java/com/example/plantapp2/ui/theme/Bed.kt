package com.example.plantapp2.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantapp2.R


/**
 *
 */


//Controls the entire setup of the page! Only alter the placements of things in this one!
@Composable
fun CenteredBed(length: Int, width: Int, gridSize: Int = 60) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDAD7CD))
            .verticalScroll(rememberScrollState())
    )
    {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally // Center the grid horizontally
        ) {
            Spacer(modifier = Modifier.height(90.dp))

            Bed(length = length, width = width, gridSize = gridSize)
        }

    }

}



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
            .padding(6.dp)  //adds the space between the bed and the outer walls of the page
            //.background(color = brown)
            .testTag("Grid")
    )
    {
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

                    GridCell(
                        row = rowIndex,
                        column = columnIndex,
                        gridSize = gridSize,
                        imageId =  R.drawable.dirt,  //remove if not working
                        isSelected = isSelected) {
                        // Toggle the state in the ViewModel when the cell is clicked
                        viewModel.toggleCell(rowIndex, columnIndex)
                    }
                }
            }
        }
    }
}




@Composable
fun GridCell(
    row: Int,
    column: Int,
    gridSize: Int,
    imageId: Int,
    isSelected: Boolean,
    onClick: () -> Unit

) {

    Box(
        modifier = Modifier
            .size(gridSize.dp)
            .background(if (isSelected) Color.Green else Color.Transparent)
            .clickable { onClick() }
            .testTag("Cell-$row-$column") // Add a unique tag for each cell
    ) {
        //image background for each cell (not ideal solution right now)
        Image (
            painter = painterResource(id = R.drawable.dirt),
            contentDescription = "Grid cell image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(0.dp)
                .size(gridSize.dp-4.dp)
                .fillMaxSize()
                //.clipToBounds()
        )


    }
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
