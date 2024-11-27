package com.example.plantapp2.ui.theme.plantPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel


/**
 * @author s235064
 * @param //PlantPage
 * @return the information page of a plant
 */

@Composable
fun PlantInfoPage(navController: NavController, modifier: Modifier = Modifier, getPlantName: String?, viewModel: PlantsViewModel = viewModel()) {
    var plant by remember { mutableStateOf<Plant?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val plantName = plant?.name
    val imageUrl = plant?.img
    val nameLatin = plant?.nameLatin
    val info = plant?.info
    val water = plant?.water
    val sun = plant?.sun
    val gradeText = plant?.gradeText

    LaunchedEffect(getPlantName) {
        if (getPlantName != null) {
            try {
                plant = viewModel.getPlantDetailsByName(getPlantName)
            } catch (e: Exception) {
                errorMessage = "Error fetching plant details: ${e.message}"
            }
        }
    }
    when {
        errorMessage != null -> {
            Text("Error: $errorMessage", modifier = Modifier.padding(16.dp))
        }

        plant == null -> {
            Text("Loading...", modifier = Modifier.padding(16.dp))
        }

        else -> {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 25.dp) // Uniform padding for the entire page
            ) {
                Spacer(modifier = Modifier.height(2.dp))

                Row{
                    BackButton(navController = navController)
                }

                Box (
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ){ imageUrl?.let {
                    PlantImage(
                        url = it
                    )
                } }
                // Plant Image


                Spacer(modifier = Modifier.height(5.dp))

                // Plant Name and Latin Name
                plantName.let { plantName ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            nameLatin?.let { latinName ->
                                PageTitleLatin(
                                    nameLatin = latinName,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Spacer(modifier = Modifier.width(120.dp))

                            LikeImage(
                                modifier = Modifier.size(50.dp),
                                plant = plant!!,
                                viewModel = viewModel
                            )                        }
                            Column {
                                if (plantName != null) {
                                    PageTitle(name = plantName)
                                }
                                nameLatin?.let { latinName ->
                                    PageTitleLatin(
                                        nameLatin = latinName
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),

                                horizontalAlignment = Alignment.End
                            ) {
                                LikeImage()

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                ) {
                    // Info Section
                    info?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column {
                                Text(
                                    text = "Info",
                                    modifier = Modifier,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.DarkGray
                                    )
                                )

                                InfoText(information = it)
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    //Grading Section
                    gradeText?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            GradeImage()
                            Spacer(modifier = Modifier.width(8.dp))
                            GradeText(information = it)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Watering Section
                    water?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            WaterCanImage()
                            Spacer(modifier = Modifier.width(8.dp))
                            WaterCanText(information = it)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Sunlight Section
                    sun?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            SunImage()
                            Spacer(modifier = Modifier.width(8.dp))
                            SunText(information = it)
                        }
                    }

                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }


//The name of the plant
@Composable
fun PageTitle(name: String, modifier: Modifier) {
    val textBoxModifier = Modifier
        //.offset(x = 20.dp, y = 260.dp)   //to move the text box
    Text(
        text = " $name",
        modifier = textBoxModifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = Color.DarkGray

        )
    )

}


//The name of the plant
@Composable
fun PageTitleLatin(nameLatin: String, modifier: Modifier) {
    Text(
        text = " $nameLatin",
        modifier = Modifier,
           // .offset(x = 0.dp, y = 50.dp),
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 20.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.DarkGray

        )
    )

}


@Composable
fun PlantImage(url: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .offset(x = 150.dp, y = 10.dp) // Position the entire component
            .size(240.dp) // Outer frame size (largest border)
            .clip(CircleShape) // Ensures the shape is circular
            .background(Color.Transparent) // Transparent background to ensure proper layering
            .drawBehind {
                // Draw the dotted border
                drawCircle(
                    color = Color(0xFF344e41), // Dotted border color
                    radius = size.minDimension / 2, // Use half the size for a perfect circle
                    style = Stroke(
                        width = 4.dp.toPx(), // Dotted border thickness
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // Dotted effect
                    )
                )
            },
        contentAlignment = Alignment.Center // Ensures all child elements are centered
    ) {
        // Solid border with shadow
        Box(
            modifier = Modifier
                .size(220.dp) // Slightly smaller for the solid border
                .clip(CircleShape) // Ensures the solid border is circular
                .background(Color(0xFF344e41)) // Solid border color
                .shadow(
                    elevation = 8.dp, // Shadow for the solid border
                    shape = CircleShape,
                    ambientColor = Color.Gray.copy(alpha = 0.6f),
                    spotColor = Color.Black.copy(alpha = 0.3f)
                ),
            contentAlignment = Alignment.Center
        ) {
            // Plant image inside the solid border
            AsyncImage(
                model = url,
                contentDescription = "Plant Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp) // Image size
                    .clip(CircleShape) // Circular clipping for the image
            )
        }
    }
}



//Information image about the best matched plants
@Composable
fun InformationImage(modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        //.offset(x = 20.dp, y = 360.dp)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.book),
            contentDescription = "Information Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 45.dp, height = 45.dp)

        )
    }
}


//Information text box about the best matched plants
@Composable
fun InfoText(information: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            //.offset(x = 65.dp, y =355.dp)
            .padding(8.dp)
    ) {
        val maxWidth = 200.dp
        val maxHeight = maxHeight

    Text(
        text = " $information",
        modifier = Modifier
            .widthIn(max = maxWidth)
            .heightIn(max = maxHeight)
            .padding(4.dp),
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Default,
            color = Color.DarkGray
        )
    )
    }
}

//Watering amount image
@Composable
fun WaterCanImage(modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        //.offset(x = 15.dp, y = 510.dp)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.watercan),
            contentDescription = "Information Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 50.dp, height = 50.dp)

        )
    }
}



//Water can text box
@Composable
fun WaterCanText(information: Int, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            //.offset(x = 65.dp, y = 520.dp)
            //.background(Color.White)
            //.border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp)
    ) {
        val maxWidth = 200.dp
        val maxHeight = maxHeight

        Text(
            text = "Must be watered (days): $information",
            modifier = Modifier
                .widthIn(max = maxWidth)
                .heightIn(max = maxHeight)
                .padding(4.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Default,
                color = Color.DarkGray
            )
        )
    }
}


//Sun image to show how much sun the plant needs
@Composable
fun SunImage(modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        //.offset(x = 20.dp, y = 600.dp)
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




//Sun text box
@Composable
fun SunText(information: Int, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            //.offset(x = 65.dp, y = 600.dp)
            //.background(Color.White)
            //.border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp)
    ) {
        val maxWidth = 200.dp
        val maxHeight = maxHeight

        Text(
            text = "Must receive sun (hours): $information",
            modifier = Modifier
                .widthIn(max = maxWidth)
                .heightIn(max = maxHeight)
                .padding(4.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Default,
                color = Color.DarkGray
            )
        )
    }
}


//Grade scale image to show if its easy or hard
@Composable
fun GradeImage(modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        //.offset(x = 20.dp, y = 440.dp)
    val imageModifierGrade = Modifier
        .size(width = 70.dp, height = 70.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Blue)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.grade),
            contentDescription = "grade Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 45.dp, height = 45.dp)

        )
    }

}

@Composable
fun Lorem(information: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            //.offset(x = 65.dp, y = 440.dp)
            //.background(Color.White)
            //.border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp)
    ) {
        val maxWidth = 200.dp
        val maxHeight = maxHeight

        Text(
            text = "Is graded to be: $information",
            modifier = Modifier
                .widthIn(max = maxWidth)
                .heightIn(max = maxHeight)
                .padding(4.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Default,
                color = Color.DarkGray
            )
        )
    }
}

//Grade text box
@Composable
fun GradeText(information: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            //.offset(x = 65.dp, y = 440.dp)
            //.background(Color.White)
            //.border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp)
    ) {
        val maxWidth = 200.dp
        val maxHeight = maxHeight

        Text(
            text = "Is graded to be: $information",
            modifier = Modifier
                .widthIn(max = maxWidth)
                .heightIn(max = maxHeight)
                .padding(4.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Default,
                color = Color.DarkGray
            )
        )
    }
}


//Helper function to test LikeImage
fun toggleLikeState(currentState: Boolean): Boolean {
    return !currentState
}

@Composable
fun LikeImage(
    modifier: Modifier = Modifier,
    plant: Plant,
    viewModel: PlantsViewModel
) {
    val context = LocalContext.current
    var isSelect by remember { mutableStateOf(plant.isLiked) } // Default state from the plant

    // Check if the plant is already liked in the favorites JSON
    LaunchedEffect(Unit) {
        val likedPlants = viewModel.getLikedPlants(context) // Fetch from JSON
        isSelect = likedPlants.any { it.name == plant.name } // Set state based on JSON
    }

    Box(modifier = modifier) {
        if (isSelect) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Liked",
                tint = Color.Red,
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        isSelect = false
                        viewModel.savePlant(context, plant.copy(isLiked = false)) // Remove from favorites
                    }
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Not Liked",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        isSelect = true
                        viewModel.savePlant(context, plant.copy(isLiked = true)) // Add to favorites
                    }
            )
        }
    }
}





@Composable
fun BackButton(navController: NavController, modifier: Modifier) {
    Box (
        modifier = Modifier
            .fillMaxSize()

    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                //.align(Alignment.TopStart)
                //.padding(16.dp)
                //.offset(4.dp, 15.dp)  //to move it by specific coordinates
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                tint = Color.DarkGray,
                contentDescription = "Go Back",
                modifier = Modifier.size(100.dp),

            )
        }
    }
}
