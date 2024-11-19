package com.example.plantapp2.ui.theme

import androidx.compose.foundation.BorderStroke
import coil.compose.AsyncImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.plantapp2.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await




/**
 * @author s235064
 * @param PlantPage
 * @return the information page of a plant
 */



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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
    ) {
        // Row for BackButton and LikeImage
        Row(
            modifier = Modifier
                //.fillMaxWidth()
                .padding(vertical = 30.dp) // Padding around the row
                .background(Color.Yellow),
            horizontalArrangement = Arrangement.SpaceBetween, // Push elements to corners
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back button (left corner)
            BackButton(
                navController = navController,
                modifier = Modifier
                    .size(40.dp) // Adjust button size if needed

            )

            // Like button (right corner)
            LikeIcon(
                modifier = Modifier
                    .size(40.dp) // Adjust button size if needed

            )
        }


        //Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),  //DONT CHANGE, will make the scroll stop working
            horizontalAlignment = Alignment.Start

            ) {

            //Plant name
            when {
                name != null -> {
                    PageTitle(
                        name = name!!,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 10.dp, bottom = 4.dp)

                    )
                }
            }

            //Plant photo
            when {
                imageUrl != null -> {
                    PlantImage(
                        url = imageUrl!!,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )

                }
            }


            //Information image
            InformationImage(
                url = "info",
                modifier = Modifier
                    .padding(bottom = 1.dp)
            )

            //Plant information box
            when {
                info != null -> {
                    InfoText(
                        information = info!!,
                        modifier = Modifier
                    )
                }
            }

            //Watering can image
            WaterCanImage(url = "Watercan", modifier = Modifier)

            //Water can text
            when {
                water != null -> {
                    WaterCanText(information = water!!, modifier = Modifier)
                }
            }

            //Sun image
            SunImage(url = "Sun", modifier = Modifier)

            //Sun text
            when {
                sun != null -> {
                    SunText(information = sun!!, modifier = Modifier)
                }
            }


            //Depth image
            GradeImage(url = "grade", modifier = Modifier)

            //Grade text
            when {
                gradeText != null -> {
                    GradeText(information = gradeText!!, modifier = Modifier)
                }
            }

        }
    }
}







@Composable
fun PageTitle(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Name: $name",
        modifier = modifier,
            //.padding(top = 50.dp, bottom = 4.dp), // Add spacing below the title
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = Color.DarkGray
        )
    )
}

@Composable
fun PlantImage(url: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(200.dp) // Set the size directly
            .padding(bottom = 16.dp) // Add spacing below the image
    ) {
        AsyncImage(
            model = url,
            contentDescription = "PlantImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize() // Fill the box
        )
    }
}


@Composable
fun InformationImage(url: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(70.dp) // Fixed size
            .padding(bottom = 16.dp) // Add spacing
    ) {
        AsyncImage(
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXye4JJDMVlIFZm6MDBiQLabIMtHhGQjNguw&s",
            contentDescription = "Information Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize() // Fill the box
        )
    }
}

@Composable
fun InfoText(information: String, modifier: Modifier = Modifier) {
    Text(
        text = "Information about the: $information",
        modifier = modifier
            .fillMaxWidth() // Use the available width
            .padding(16.dp), // Add padding around the text
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Default,
            color = Color.DarkGray
        )
    )
}

@Composable
fun WaterCanImage(url: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(70.dp) // Fixed size
            .padding(bottom = 8.dp) // Add spacing below the image
    ) {
        AsyncImage(
            model = "https://cdn.create.vista.com/api/media/small/249600986/stock-vector-blue-watering-can-icon-sign-flat-style-design-vector-illustration",
            contentDescription = "Watering Can Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize() // Fill the box
        )
    }
}

@Composable
fun WaterCanText(information: Int, modifier: Modifier = Modifier) {
    Text(
        text = "Must be watered (days): $information",
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Default,
            color = Color.DarkGray
        )
    )
}



//Sun image to show how much sun the plant needs
@Composable
fun SunImage(url: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .size(70.dp) // Fixed size
            .padding(bottom = 8.dp) // Add spacing below the image
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
        Text(
            text = "Must receive sun: $information",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Default,
                color = Color.DarkGray
            )
        )
}



//Grade scale image to show if its easy or hard
@Composable
fun GradeImage(url: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .size(70.dp) // Fixed size
            .padding(bottom = 8.dp) // Add spacing below the image
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
        Text(
            text = "Is graded to be: $information",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            style = TextStyle(   //to edit and customize the text inside
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Default,
                color = Color.DarkGray
            )
        )
}



//Helper function to test LikeImage
fun toggleLikeState(currentState: Boolean): Boolean {
    return !currentState
}



//Like plant to list of favourites
@Composable
fun LikeIcon(modifier: Modifier = Modifier) {
    var isSelected by remember { mutableStateOf(false) }

    Icon(
        imageVector = if (isSelected) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = if (isSelected) "Liked" else "Not Liked",
        tint = if (isSelected) Color.Red else Color.Gray, // Change color based on state
        modifier = modifier
            .size(40.dp) // Set the size of the icon
            .clickable { isSelected = toggleLikeState(isSelected) } // Toggle state on click
    )
}


@Composable
fun BackButton(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the available space for proper alignment
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            //modifier = Modifier
                //.padding(16.dp)
                //.offset(4.dp, 15.dp)  //to move it by specific coordinates
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Go Back",
                modifier = Modifier
            )
        }
    }
}











/*


@Composable
fun BackgroundImage(modifier: Modifier) {
    Box(
        modifier = Modifier
            .size(width = 411.dp, height = 913.dp)
            .background(Color.LightGray)

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
            fontFamily = FontFamily.Default,
            color = Color.DarkGray

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
            //.background(Color.White)
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
            fontFamily = FontFamily.Default,
            color = Color.DarkGray
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
            //.background(Color.White)
            //.border(BorderStroke(1.dp, Color.Black))
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
                fontFamily = FontFamily.Default,
                color = Color.DarkGray
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

*/

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