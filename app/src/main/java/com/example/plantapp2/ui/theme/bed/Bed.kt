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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantapp2.R
import com.example.plantapp2.beneficial.BeneficialPlantsScreen
import com.example.plantapp2.data.localData.LocalBeds
import com.example.plantapp2.mvvm.home.GridViewModel
import com.example.plantapp2.mvvm.home.SavedBedsViewModelFactory
import com.example.plantapp2.plants.favorites.FavoritePlantsScreen
import com.example.plantapp2.ui.home.SavedBedScreen
import com.example.plantapp2.ui.home.SavedBedsViewModel
import com.example.plantapp2.ui.settings.addBed.customizeBed.ZoomableFrame
import com.example.plantapp2.ui.theme.styling.darkGreen

@Composable
fun CenteredBed(length: Int, width: Int, gridSize: Int = 60) {
    val context = LocalContext.current  // This gets the context from the Composable's environment
    val bedID: Int = 2
    val viewModel: SavedBedsViewModel = viewModel(factory = SavedBedsViewModelFactory(context, bedID ))
    val beds by viewModel.beds.collectAsState()
    val gottenBed by viewModel.specficBed.collectAsState()

    //val selectedBed =

    fun CheckBed(): LocalBeds? {
        if(beds.isEmpty()) {
            val ActualBeds: LocalBeds? = beds.firstOrNull()
            beds[2]
            return ActualBeds
        } else {
            val ActualBeds: LocalBeds = gottenBed
            return ActualBeds
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFDAD7CD))
    ) {
        Spacer(modifier = Modifier.height(1.dp))

        // Title and Header Section
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            BedPageTitle(
                modifier = Modifier.align(Alignment.CenterStart)
            )
            BundleDeco(
                modifier = Modifier.align(Alignment.TopEnd)
            )
            if (CheckBed() != null) {
                Text(
                    text = "Selected Bed: ${CheckBed()?.name ?: "None"}",
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 18.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Top Section: Render the Bed grid or show a placeholder
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (beds.isEmpty()) {
                Bed(length, width)

            } else {
                CheckBed().let { bed ->
                    Box (modifier = Modifier.padding(horizontal = 16.dp)){
                        ZoomableFrame {
                            if (bed != null) {
                                SavedBedScreen(
                                    length = bed.length,
                                    width = bed.width,
                                    cellState = bed.selectedCells
                                )
                            }
                        }
                    }
                }
            }
        }

        if (beds.isEmpty()) {
            Spacer(modifier = Modifier.height(30.dp))
        }

        // Favorites and Beneficial Plants Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if (beds.isEmpty()) {
                DottedLine()
                Spacer(modifier = Modifier.height(1.dp))
                GreenLine()
            }

            Spacer(modifier = Modifier.height(5.dp))
            FavouritesTitle(modifier = Modifier.padding(start = 16.dp))
            Spacer(modifier = Modifier.height(2.dp))
            FavoritePlantsScreen(context = context)

            DottedLine()
            Spacer(modifier = Modifier.height(1.dp))
            GreenLine()

            Spacer(modifier = Modifier.height(5.dp))
            FriendsTitle(modifier = Modifier.padding(start = 16.dp))
            Spacer(modifier = Modifier.height(2.dp))
            BeneficialPlantsScreen(context = context)

            DottedLine()
            Spacer(modifier = Modifier.height(1.dp))
            GreenLine()
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
        // Check if this is the specific tile for potatoes
        if (row == 2 && column == 3) {
            // Show potato image for the hardcoded tile
            Image(
                painter = painterResource(id = R.drawable.potato_bed), // Potato image
                contentDescription = "Potato plant",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else if (row == 1 && column == 1){
            // Default to the dirt image
            Image(
                painter = painterResource(id = R.drawable.celery_bed), // Celery image
                contentDescription = "celery plant",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else if (row == 1 && column == 4){
            // Default to the dirt image
            Image(
                painter = painterResource(id = R.drawable.cauliflower_bed), // Cauliflower image
                contentDescription = "cauliflower plant",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else if (row == 2 && column == 1){
            // Default to the dirt image
            Image(
                painter = painterResource(id = R.drawable.tomato_bed), // Tomato image
                contentDescription = "tomato plant",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
        // Default to the dirt image
        Image(
            painter = painterResource(id = R.drawable.dirt),
            contentDescription = "Dirt tile",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
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
        text = "My Favorites ",
        modifier = Modifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray

        )
    )

}
@Composable
fun FriendsTitle(modifier: Modifier) {
    Text(
        text = "Beneficial plants",
        modifier = Modifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
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
                color = darkGreen,
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
            .offset(x = 210.dp, y = 20.dp)
    )
}



