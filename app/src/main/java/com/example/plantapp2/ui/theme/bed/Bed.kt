package com.example.plantapp2.ui.theme.bed

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantapp2.R
import com.example.plantapp2.mvvm.home.GridViewModel


/**
 *@author s235064
 * @param CenteredBed
 *
 */

@Composable
fun CenteredBed(length: Int, width: Int, gridSize: Int = 60) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Main vertical scrolling for the page
            .background(Color(0xFFDAD7CD))
    ) {

        Spacer(modifier = Modifier.height(1.dp)) // Space at the top

        //The veggie image and title
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ){
            BedPageTitle(
                modifier = Modifier
                    .align(Alignment.CenterStart)

            )
            BundleDeco(
                modifier = Modifier
                    .align(Alignment.TopEnd)

            )
        }



        //Top section: Title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Add horizontal padding
        ) {

        Spacer(modifier = Modifier.height(30.dp))

        }


        // Top Section: Garden Bed
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center // Center the grid horizontally
        ) {
            Bed(length = length, width = width, gridSize = gridSize)
        }

        Spacer(modifier = Modifier.height(30.dp)) // Space between sections


        // Middle Section: Favourites title and other components
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Add horizontal padding
        ) {
            DottedLine()
            Spacer(modifier = Modifier.height(1.dp))
            GreenLine()
            Spacer(modifier = Modifier.height(10.dp))
            FavouritesTitle(modifier = Modifier.padding(start = 16.dp)) // Slight offset for alignment
            Spacer(modifier = Modifier.height(10.dp))
            GreenLine()
            Spacer(modifier = Modifier.height(2.dp))
            DottedLine()

            Spacer(modifier = Modifier.height(300.dp)) // Space for extra content
            testImage(modifier = Modifier.align(Alignment.CenterHorizontally)) // Center the test image
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
            .clip(RoundedCornerShape(6.dp))
            .shadow(8.dp, RoundedCornerShape(6.dp))
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
    Canvas(modifier = Modifier) {
        drawGridLines(gridSize)
    }
}




@Composable
fun DrawGridCells(numRows: Int, numColumns: Int, gridSize: Int, viewModel: GridViewModel) {
    // Use a fixed layout (not scrollable) for rows and columns
    Column {
        repeat(numRows) { rowIndex ->
            Row {
                repeat(numColumns) { columnIndex ->
                    // Get the clicked state from the ViewModel
                    val isSelected = viewModel.gridState[rowIndex][columnIndex]

                    GridCell(
                        row = rowIndex,
                        column = columnIndex,
                        gridSize = gridSize,
                        imageId = R.drawable.dirt, // Replace if needed
                        isSelected = isSelected
                    ) {
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
            .testTag("Cell-$row-$column")
    ) {
        Image(
            painter = painterResource(id = R.drawable.dirt),
            contentDescription = "Grid cell image",
            contentScale = ContentScale.Crop,
            modifier = Modifier  //.fillMaxSize()
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


/**
 * @author s235064
 * @param FavouritesTitle
 */

@Composable
fun FavouritesTitle(modifier: Modifier) {
    Text(
        text = "My Favourites ",
        modifier = Modifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = Color.DarkGray

        )
    )

}


/**
 * @author s235064
 * @param DottedLine
 */

@Composable
fun DottedLine() {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(2.dp) // Thickness of the line
    ) {
        val lineLength = 4.dp.toPx()
        val spaceLength = 4.dp.toPx()
        val canvasWidth = size.width

        var startX = 0f
        while (startX < canvasWidth) {
            drawLine(
                color = Color(0xFF344e41),
                start = androidx.compose.ui.geometry.Offset(startX, 0f),
                end = androidx.compose.ui.geometry.Offset(startX + lineLength, 0f),
                strokeWidth = 2.dp.toPx()
            )
            startX += lineLength + spaceLength
        }
    }
}




//Remove when favourites list is needed
@Composable
fun testImage(modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.sun),
            contentDescription = "Information Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 45.dp, height = 45.dp)

        )
    }
}



@Composable
fun BedPageTitle(modifier: Modifier) {
        Text(
            text = "My Garden",
            modifier = modifier
                .offset(x=10.dp, y=75.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Default,
                color = Color.DarkGray

            )
        )
    }




@Composable
fun GreenLine() {
    Canvas (
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
    ){
        drawLine(
            color = Color(0xFF344E41), // Green color for the line
            start = androidx.compose.ui.geometry.Offset(0f, size.height / 2), // Start at the left
            end = androidx.compose.ui.geometry.Offset(size.width, size.height / 2), // End at the right
            strokeWidth = 4.dp.toPx() // Thickness of the line
        )
    }

}

@Composable
fun BundleDeco(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.bundle),
        contentDescription = null,
        modifier = Modifier
            .size(140.dp)
            .offset(x=210.dp, y=20.dp)
    )
}



