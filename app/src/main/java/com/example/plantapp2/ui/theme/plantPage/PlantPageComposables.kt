package com.example.plantapp2.ui.theme.plantPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.plantapp2.R
import com.example.plantapp2.ui.theme.styling.darkGreen


@Composable
fun PageTitle(name: String) {
    Text(
        text = name,
        modifier = Modifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray

        )
    )
}


//The name of the plant
@Composable
fun PageTitleLatin(nameLatin: String) {
    Text(
        text = nameLatin,
        modifier = Modifier,
        style = TextStyle(
            fontSize = 25.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.DarkGray

        )
    )
}

@Composable
fun PlantImage(url: String) {
    Box(
        modifier = Modifier
            //.offset(x = 150.dp, y = 10.dp) // Position the entire component
            .size(240.dp) // Outer frame size (largest border)
            .clip(CircleShape) // Ensures the shape is circular
            .drawBehind {
                // Draw the dotted border
                drawCircle(
                    color = (darkGreen), // Dotted border color
                    radius = size.minDimension / 2, // Use half the size for a perfect circle
                    style = Stroke(
                        width = 4.dp.toPx(), // Dotted border thickness
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(10f, 10f),
                            0f
                        )
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
                .background(darkGreen) // Solid border color
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
fun InformationImage() {
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
    Text(
        text = information,
        modifier = Modifier,
        style = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Default,
            color = Color.DarkGray
        )
    )
}

//Watering amount image
@Composable
fun WaterCanImage() {
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
                .size(width = 45.dp, height = 45.dp)

        )
    }
}

//Water can text box
@Composable
fun WaterCanText(information: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(8.dp)
    ) {

        Text(
            text = "Must be watered (inches per week): $information",
            modifier = Modifier
                //.widthIn(max = maxWidth)
                //.heightIn(max = maxHeight)
                .padding(4.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 17.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Default,
                color = Color.DarkGray
            )
        )
    }
}


//Sun image to show how much sun the plant needs
@Composable
fun SunImage() {
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
    Box(
        modifier = modifier
            .padding(8.dp)
    ) {

        Text(
            text = "Must receive sun (hours per day): $information",
            modifier = Modifier
                .padding(4.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 17.sp,
                fontWeight = FontWeight.Light,
                color = Color.DarkGray
            )
        )
    }
}



//Grade scale image to show if its easy or hard
@Composable
fun GradeImage() {
    val boxModifier = Modifier   //how to move the box with the potato image around
    //.offset(x = 20.dp, y = 440.dp)
    Modifier
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
            text = "Difficulty: $information",
            modifier = Modifier
                .widthIn(max = maxWidth)
                .heightIn(max = maxHeight)
                .padding(4.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 17.sp,
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
fun LikeImage() {
    var isSelect by remember { mutableStateOf(false) }
    Modifier
        //.size(50.dp)
        .clickable { isSelect = !isSelect }

    Box(modifier = Modifier) {
        if (isSelect) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Like",
                tint = Color.Red,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.TopEnd)
                    //.offset(x = 290.dp, y = 270.dp)
                    .clickable { isSelect = !isSelect }
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Unlike",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.TopEnd)
                    //.offset(x = 290.dp, y = 270.dp)
                    .clickable { isSelect = !isSelect }
            )
        }
    }
}


@Composable
fun BackButton(navController: NavController) {
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

