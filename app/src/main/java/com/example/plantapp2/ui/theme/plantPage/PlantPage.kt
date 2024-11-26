package com.example.plantapp2.ui.theme.plantPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.plantapp2.R
import com.example.plantapp2.data.Plant
//import com.example.plantapp2.data.FirebaseCallback
import com.example.plantapp2.plants.PlantsViewModel


/**
 * @author s235064
 * @param PlantPage
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
    val lorem = "Lorem Ipsum er ganske enkelt fyldtekst fra print- og typografiindustrien. Lorem Ipsum har været standard fyldtekst siden 1500-tallet, hvor en ukendt trykker sammensatte en tilfældig spalte for at trykke en bog til sammenligning af forskellige skrifttyper. Lorem Ipsum har ikke alene overlevet fem århundreder, men har også vundet indpas i elektronisk typografi uden væsentlige ændringer. Sætningen blev gjordt kendt i 1960'erne med lanceringen af Letraset-ark, som indeholdt afsnit med Lorem Ipsum, og senere med layoutprogrammer som Aldus PageMaker, som også indeholdt en udgave af Lorem Ipsum.\n" +
            "\n" +
            "Hvorfor bruger vi det?\n" +
            "Det er en kendsgerning, at man bliver distraheret af læsbart indhold på en side, når man betragter dens layout. Meningen med at bruge Lorem Ipsum er, at teksten indeholder mere eller mindre almindelig tekstopbygning i modsætning til \"Tekst her - og mere tekst her\", mens det samtidigt ligner almindelig tekst. Mange layoutprogrammer og webdesignere bruger Lorem Ipsum som fyldtekst. En søgning på Lorem Ipsum afslører mange websider, som stadig er på udviklingsstadiet. Der har været et utal af variationer, som er opstået enten på grund af fejl og andre gange med vilje (som blandt andet et resultat af humor).\n" +
            "\n" +
            "\n" +
            "Hvor kommer det fra?\n" +
            "I modsætning til hvad mange tror, er Lorem Ipsum ikke bare tilfældig tekst. Det stammer fra et stykke litteratur på latin fra år 45 f.kr., hvilket gør teksten over 2000 år gammel. Richard McClintock, professor i latin fra Hampden-Sydney universitet i Virginia, undersøgte et af de mindst kendte ord \"consectetur\" fra en del af Lorem Ipsum, og fandt frem til dets oprindelse ved at studere brugen gennem klassisk litteratur. Lorem Ipsum stammer fra afsnittene 1.10.32 og 1.10.33 fra \"de Finibus Bonorum et Malorum\" (Det gode og ondes ekstremer), som er skrevet af Cicero i år 45 f.kr. Bogen, som var meget populær i renæssancen, er en afhandling om etik. Den første linie af Lorem Ipsum \"Lorem ipsum dolor sit amet...\" kommer fra en linje i afsnit 1.10.32.\n" +
            "\n" +
            "Standardafsnittet af Lorem Ipsum, som er brugt siden 1500-tallet, er gengivet nedenfor for de, der er interesserede. Afsnittene 1.10.32 og 1.10.33 fra \"de Finibus Bonorum et Malorum\" af Cicero er også gengivet i deres nøjagtige udgave i selskab med den engelske udgave fra oversættelsen af H. Rackham fra 1914."

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
            // Plant is available, display its details
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Name: ${plant?.name}", modifier = Modifier.padding(bottom = 8.dp))
                Text("Latin Name: ${plant?.nameLatin}", modifier = Modifier.padding(bottom = 8.dp))
                Text(
                    "Info: ${plant?.info ?: "No additional information"}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Sunlight: ${plant?.sun ?: "Not specified"}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "Water: ${plant?.water ?: "Not specified"}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )


            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFDAD7CD))
                    .padding(horizontal = 8.dp) // Uniform padding for the entire page
            ) {
                Spacer(modifier = Modifier.height(2.dp))

                // Back Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                ) {
                    BackButton(navController = navController, modifier = Modifier)
                }

                Spacer(modifier = Modifier.height(1.dp))

                // Plant Image
                imageUrl?.let {
                    PlantImage(
                        url = it, modifier = Modifier
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                // Plant Name and Latin Name
                plantName.let { plantName ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 4.dp,
                                end = 4.dp
                            ) // Align to the start with some padding
                    ) {
                        if (plantName != null) {
                            PageTitle(name = plantName, modifier = Modifier.align(Alignment.Start))
                        }

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

                            LikeImage(modifier = Modifier.size(50.dp))
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
                            InformationImage(modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            InfoText(information = it)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Grading Section
                    gradeText?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            GradeImage(modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            GradeText(information = it)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Watering Section
                    water?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            WaterCanImage(modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            WaterCanText(information = it)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Sunlight Section
                    sun?.let {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            SunImage(modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            SunText(information = it)
                        }
                    }

                    lorem.let {Row(verticalAlignment = Alignment.CenterVertically) {
                        SunImage(modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Lorem(information = it)
                    }
                    }}




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
fun LikeImage(modifier : Modifier = Modifier){
    var isSelect by remember { mutableStateOf(false) }
    val iconModifier = Modifier
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
