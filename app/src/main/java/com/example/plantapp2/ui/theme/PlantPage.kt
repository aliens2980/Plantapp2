package com.example.plantapp2.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
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
import androidx.compose.ui.graphics.Color
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
            info = result.getString("info") //Fetch info from Firestore
            water = result.getLong("water")?.toInt()
            gradeText = result.getString("gradeText")
            sun = result.getLong("sun")?.toInt()
        } catch (e: Exception) {
            errorMessage = "Failed to load image: ${e.message}" // Capture error message
        } finally {
            isLoading = false // Set loading state to false
        }
    }


    //Our box layer to allow layering
    Box(modifier = Modifier.fillMaxSize()) {
        //Our background
        BackgroundImage(url = "background", modifier = Modifier)

        //Plant name
        when { name != null -> { PageTitle(name = name!!, modifier = Modifier.align(Alignment.TopCenter)) }}

        //Plant photo
        when { imageUrl != null -> { PlantImage(url = imageUrl!!, modifier = Modifier.align(Alignment.TopCenter)) }}

        //Plant information box
        when { info != null -> { InfoText(information = info!!, modifier = Modifier) }}


        //Information image
        InformationImage(url = "info", modifier = Modifier)
        //Watering can image
        WaterCanImage(url = "Watercan", modifier = Modifier)

        //Water can text
        when { water != null -> { WaterCanText(information = water!!, modifier = Modifier) }}

        //Sun image
        SunImage(url = "Sun", modifier = Modifier)

        //Sun text
        when { sun != null -> { SunText(information = sun!!, modifier = Modifier)}}


        //Depth image
        GradeImage(url = "grade", modifier = Modifier)

        //Grade text
        when { gradeText != null -> { GradeText(information = gradeText!!, modifier = Modifier)}}


        //Like image
        LikeImage()
        //The back button
        BackButton(navController = navController)
    }
}



@Composable
fun BackgroundImage(url: String, modifier: Modifier) {
    AsyncImage(
        model = "https://t4.ftcdn.net/jpg/00/14/74/45/360_F_14744561_RJDuXs5eCrpEHMTg3qduWKRy5ExJJc1b.jpg",
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,   //this makes us able to crop the picture into the size we want by .size
        modifier = Modifier
            .size(width = 411.dp, height = 913.dp)

    )
}


//The page title text box
@Composable
fun PageTitle(name: String, modifier: Modifier) {
    val textBoxModifier = Modifier
        .offset(x = 70.dp, y = 65.dp)   //to move the text box
    Text(
        text = "Name: $name",
        modifier = textBoxModifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = Color.White

        )
    )
}


@Composable
fun PlantImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 70.dp, y = 100.dp)
    Box(
        modifier = boxModifier
    ) {
        //NEW DATABASE IMAGE
        AsyncImage(
            model = url,
            contentDescription = "PlantImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 200.dp, height = 200.dp) // Adjust height as needed
        )
    }
}



//Information image
@Composable
fun InformationImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 340.dp)
    Box(
        modifier = boxModifier
    ) {
        AsyncImage(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXye4JJDMVlIFZm6MDBiQLabIMtHhGQjNguw&s",
            //painter = painterResource(id = R.drawable.potato),
            contentDescription = "Information Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 70.dp, height = 70.dp)

        )
    }
}


//Information text box
@Composable
fun InfoText(information: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .offset(x = 140.dp, y = 340.dp)
            .background(Color.White)
            .padding(8.dp)
    ) {
        val maxWidth = 200.dp
        val maxHeight = maxHeight

    Text(
        text = "Information about the: $information",
        modifier = Modifier
            .widthIn(max = maxWidth)
            .heightIn(max = maxHeight)
            .padding(4.dp),
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif
        )
    )
    }
}

//Watering amount image
@Composable
fun WaterCanImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 520.dp)
    Box(
        modifier = boxModifier
    ) {
        AsyncImage(
            model = "https://cdn.create.vista.com/api/media/small/249600986/stock-vector-blue-watering-can-icon-sign-flat-style-design-vector-illustration",
            //painter = painterResource(id = R.drawable.potato),
            contentDescription = "Information Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 70.dp, height = 70.dp)

        )
    }

}



//Water can text box
@Composable
fun WaterCanText(information: Int, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .offset(x = 140.dp, y = 520.dp)
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Black))
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
                fontFamily = FontFamily.Serif
            )
        )
    }
}


//Sun image to show how much sun the plant needs
@Composable
fun SunImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 610.dp)
    Box(
        modifier = boxModifier
    ) {
        AsyncImage(
            model = "https://static.vecteezy.com/system/resources/previews/007/956/515/non_2x/animated-sun-icon-in-white-background-vector.jpg",
            //painter = painterResource(id = R.drawable.potato),
            contentDescription = "Information Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 70.dp, height = 70.dp)

        )
    }
}


//Sun text box
@Composable
fun SunText(information: Int, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .offset(x = 140.dp, y = 610.dp)
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp)
    ) {
        val maxWidth = 200.dp
        val maxHeight = maxHeight

        Text(
            text = "Must receive sun: $information",
            modifier = Modifier
                .widthIn(max = maxWidth)
                .heightIn(max = maxHeight)
                .padding(4.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Serif
            )
        )
    }
}


//Grade scale image to show if its easy or hard
@Composable
fun GradeImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 430.dp)
    val imageModifierGrade = Modifier
        .size(width = 70.dp, height = 70.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Blue)
    Box(
        modifier = boxModifier
    ) {
        AsyncImage(
            model = "https://images.pond5.com/low-risk-gauge-level-animation-footage-236417204_iconl.jpeg",
            //painter = painterResource(id = R.drawable.potato),
            contentDescription = "grade Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 70.dp, height = 70.dp)

        )
    }

}


//Grade text box
@Composable
fun GradeText(information: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .offset(x = 140.dp, y = 430.dp)
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Black))
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
                fontFamily = FontFamily.Serif
            )
        )
    }
}


//Helper function to test LikeImage
fun toggleLikeState(currentState: Boolean): Boolean {
    return !currentState
}


//Like plant to list of favourites
@Composable
fun LikeImage() {
    var isSelected by remember { mutableStateOf(false) }
    val boxModifier = Modifier   //how to move the box with the like image around
        .offset(x = 280.dp, y = 30.dp)
    val imageModifierLike = Modifier
        .size(width = 70.dp, height = 70.dp)
    Box(
        modifier = boxModifier.clickable{
                isSelected = toggleLikeState(isSelected)
            }
    ) {
        if(isSelected)
            Image(
                painter = painterResource(id = R.drawable.like4),
                contentDescription = "Like Image Filled",
                contentScale = ContentScale.FillWidth,
                modifier = imageModifierLike,
            ) else
            Image(
                painter = painterResource(id = R.drawable.like3),
                contentDescription = "Like Image Empty",
                contentScale = ContentScale.FillWidth,
                modifier = imageModifierLike

            )

    }

}


@Composable
fun BackButton(navController: NavController) {
    Box (
        modifier = Modifier.fillMaxSize()
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
                contentDescription = "Go Back",
                modifier = Modifier.size(100.dp)
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