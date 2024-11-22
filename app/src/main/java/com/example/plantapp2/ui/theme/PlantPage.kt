package com.example.plantapp2.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.plantapp2.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


/**
 * @author s235064
 * @param PlantPage
 * @return the information page of a plant
 */



//The name of the plant
@Composable
fun PlantInfoPage(navController: NavController, modifier: Modifier = Modifier) {
    // State for storing the image URL and loading state
    var imageUrl by remember { mutableStateOf<String?>(null) }
    var name by remember { mutableStateOf<String?>(null) }
    var nameLatin by remember { mutableStateOf<String?>(null) }   //FIX IT UP ON FIREBASE
    var info by remember { mutableStateOf<String?>(null) }
    var water by remember { mutableStateOf<Int?>(null) }
    var gradeText by remember { mutableStateOf<String?>(null) }
    var sun by remember { mutableStateOf<Int?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val firestore = FirebaseFirestore.getInstance()

    // Load image URL from Firestore when this Composable is first displayed
    LaunchedEffect(Unit) {
        try {
            // Fetch the image URL from Firestore
            val result = firestore.collection("plants")
                .document("0")
                .get()
                .await()
            imageUrl = result.getString("img") // Fetch the URL field from Firestore
            name = result.getString("name") //Fetch name from Firestore
            nameLatin = result.getString("nameLatin") //fetch latin name from Firestore
            info = result.getString("info") //Fetch info from Firestore
            water = result.getLong("water")?.toInt()
            gradeText = result.getString("gradeText")
            sun = result.getLong("sun")?.toInt()  //chage to best matches
        } catch (e: Exception) {
            errorMessage = "Failed to load image: ${e.message}" // Capture error message
        } finally {
            isLoading = false // Set loading state to false
        }
    }


    //Our box layer to allow layering
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDAD7CD)) //the background color of the page

    ) {
        //Our background
        //BackgroundImage(url = "background", modifier = Modifier)

        //Plant name
        when { name != null -> { PageTitle(name = name!!, modifier = Modifier.align(Alignment.TopCenter)) }}


        //Plant name latin
        when { nameLatin != null -> { PageTitleLatin(nameLatin = nameLatin!!, modifier = Modifier.align(Alignment.TopCenter)) }}

        //Plant photo
        when { imageUrl != null -> { PlantImage(url = imageUrl!!, modifier = Modifier.align(Alignment.TopCenter)) }}

        //Plant information box
        when { info != null -> { InfoText(information = info!!, modifier = Modifier) }}


        //Information image
        InformationImage(modifier = Modifier)
        //Watering can image
        WaterCanImage(modifier = Modifier)

        //Water can text
        when { water != null -> { WaterCanText(information = water!!, modifier = Modifier) }}

        //Sun image
        SunImage(modifier = Modifier)

        //Sun text
        when { sun != null -> { SunText(information = sun!!, modifier = Modifier)}}


        //Depth image
        GradeImage(modifier = Modifier)

        //Grade text
        when { gradeText != null -> { GradeText(information = gradeText!!, modifier = Modifier)}}


        //Like image
        LikeImage()
        //The back button
        BackButton(navController = navController)
    }
}


//The name of the plant
@Composable
fun PageTitle(name: String, modifier: Modifier) {
    val textBoxModifier = Modifier
        .offset(x = 20.dp, y = 260.dp)   //to move the text box
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
    val textBoxModifier = Modifier
        .offset(x = 20.dp, y = 299.dp)   //to move the text box
    Text(
        text = " $nameLatin",
        modifier = textBoxModifier,
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
            .offset(x = 150.dp, y = 30.dp) // Position the entire component
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
        .offset(x = 20.dp, y = 360.dp)
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
            .offset(x = 65.dp, y =355.dp)
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
        .offset(x = 15.dp, y = 510.dp)
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
            .offset(x = 65.dp, y = 520.dp)
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
        .offset(x = 20.dp, y = 600.dp)
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
            .offset(x = 65.dp, y = 600.dp)
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
        .offset(x = 20.dp, y = 440.dp)
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


//Grade text box
@Composable
fun GradeText(information: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .offset(x = 65.dp, y = 440.dp)
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
fun LikeImage(modifier : Modifier = Modifier){
    var isSelect by remember { mutableStateOf(false) }
    val iconModifier = Modifier
        .size(50.dp)
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
                    .offset(x = 290.dp, y = 270.dp)
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
                    .offset(x = 290.dp, y = 270.dp)
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
                .padding(16.dp)
                .offset(4.dp, 15.dp)  //to move it by specific coordinates
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



//TESTER OF TWO IMAGES ON TOP OF EACHOTHER
/*
@Composable
fun OverlayImagesHelper() {
    OverlayImages(
        backUrl = "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg",
        frontUrl = "https://plus.unsplash.com/premium_photo-1670271544153-dd9933f0f119?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8Ymx1ZSUyMHRleHR1cmV8ZW58MHx8MHx8fDA%3D",
        modifier = Modifier
    )
}

@Composable
fun OverlayImages(
    backUrl: String,
    frontUrl: String,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = backUrl,
            contentDescription = "background url",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .align(Alignment.Center)
        )
        AsyncImage(
            model = frontUrl,
            contentDescription = "front url",
            modifier = Modifier
                .size(50.dp,50.dp)
                .align(Alignment.Center)
        )
    }
}

*/