package com.example.plantapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantapp2.ui.theme.Plantapp2Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantapp2.ui.theme.Plantapp2Theme
import com.example.plantapp2.R



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Plantapp2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlantInfoPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



/**
 * @author s235064
 * @param PlantInfoPage
 * @return the information page of a plant
 */

//The name of the plant
@Composable
fun PlantInfoPage(modifier: Modifier = Modifier) {
    //Our box layer to allow layering
    Box(modifier = Modifier.fillMaxSize()) {
       //Our background
        BackgroundImage()
        //Other content
        PageTitle(name = "Potato", modifier = Modifier.align(Alignment.Center))
        //Plant photo
        PlantImage()
        //Watering can image
        WaterCanImage()
        //Water can text
        WaterCanText(information = "Information", modifier = Modifier) //REMEMBER TO LINK TO API DATA HERE
        //Sun image
        SunImage()
        //Sun text
        SunText(information = "Information", modifier = Modifier) //REMEMBER TO LINK TO API DATA HERE
        //Depth image
        DepthImage()
        //Depth text
        DepthText(information = "Information", modifier = Modifier) //REMEMBER TO LINK TO API DATA HERE
        //Grade image
        GradeImage()
        //Grade text
        GradeText(information = "Information", modifier = Modifier) //REMEMBER TO LINK TO API DATA HERE
        //Like image
        LikeImage()
        //Plant information box
        InfoText(information = "Information", modifier = Modifier)   //REMEMBER TO LINK TO API DATA HERE BY A VIEWMODEL SCOPE
    }
}


@Composable
fun BackgroundImage() {
    val imageModifier = Modifier
        .size(width = 411.dp, height = 913.dp)  //here you can change the background photo size
        .border(BorderStroke(1.dp, Color.Red))
        .background(Color.Blue)
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_daisy_background),
        contentDescription = "Background Image",
        contentScale = ContentScale.FillHeight,
        modifier = imageModifier
    )
}



//The page title text box
@Composable
fun PageTitle(name: String, modifier: Modifier = Modifier) {
    val textBoxModifier = Modifier
        .offset(x = 100.dp, y = 65.dp)   //to move the text box
    Text(
        text = "Name: $name",
        modifier = textBoxModifier,
        style = TextStyle(   //to edit and customize the text inside 
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    )
}


@Composable
fun PlantImage() {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 100.dp, y = 100.dp)
    val imageModifierPlant = Modifier
        .size(width = 200.dp, height = 200.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Blue)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.potato),
            contentDescription = "Plant Image",
            contentScale = ContentScale.FillWidth,
            modifier = imageModifierPlant

        )
    }

}


//Information text box
@Composable
fun InfoText(information: String, modifier: Modifier = Modifier) {
    val infoBoxModifier = modifier
        .offset(x = 100.dp, y = 400.dp)
        .background(Color.White)
    Text(
        text = "Information about the: $information",
        modifier = infoBoxModifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif
        )
    )
}

//Watering amount image
@Composable
fun WaterCanImage() {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 800.dp)
    val imageModifierWaterCan = Modifier
        .size(width = 70.dp, height = 70.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Blue)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.vandkande),
            contentDescription = "Watering Can Image",
            contentScale = ContentScale.FillWidth,
            modifier = imageModifierWaterCan

        )
    }

}


//Water can text box
@Composable
fun WaterCanText(information: String, modifier: Modifier = Modifier) {
    val waterCanBoxModifier = modifier
        .offset(x = 140.dp, y = 800.dp)
        .background(Color.White)
        .border(BorderStroke(1.dp, Color.Black))
    Text(
        text = "Must be watered: $information",
        modifier = waterCanBoxModifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif
        )
    )
}




//Sun image to show how much sun the plant needs
@Composable
fun SunImage() {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 710.dp)
    val imageModifierSun = Modifier
        .size(width = 70.dp, height = 70.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Blue)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.sun),
            contentDescription = "Sun Image",
            contentScale = ContentScale.FillWidth,
            modifier = imageModifierSun

        )
    }
}


//Sun text box
@Composable
fun SunText(information: String, modifier: Modifier = Modifier) {
    val sunBoxModifier = modifier
        .offset(x = 140.dp, y = 710.dp)
        .background(Color.White)
        .border(BorderStroke(1.dp, Color.Black))
    Text(
        text = "Must receive sun: $information",
        modifier = sunBoxModifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif
        )
    )
}





//Soil depth image to show how far down it should be planted
@Composable
fun DepthImage() {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 620.dp)
    val imageModifierDepth = Modifier
        .size(width = 70.dp, height = 70.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Blue)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.depth),
            contentDescription = "Sun Image",
            contentScale = ContentScale.FillWidth,
            modifier = imageModifierDepth

        )
    }

}



//Depth text box
@Composable
fun DepthText(information: String, modifier: Modifier = Modifier) {
    val depthBoxModifier = modifier
        .offset(x = 140.dp, y = 620.dp)
        .background(Color.White)
        .border(BorderStroke(1.dp, Color.Black))
    Text(
        text = "Must be planted at: $information",
        modifier = depthBoxModifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif
        )
    )
}




//Grade scale image to show if its easy or hard
@Composable
fun GradeImage() {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 530.dp)
    val imageModifierGrade = Modifier
        .size(width = 70.dp, height = 70.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Blue)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.grade),
            contentDescription = "Grade Image",
            contentScale = ContentScale.FillWidth,
            modifier = imageModifierGrade

        )
    }

}


//Grade text box
@Composable
fun GradeText(information: String, modifier: Modifier = Modifier) {
    val gradeBoxModifier = modifier
        .offset(x = 140.dp, y = 530.dp)
        .background(Color.White)
        .border(BorderStroke(1.dp, Color.Black))
    Text(
        text = "Is graded to be: $information",
        modifier = gradeBoxModifier,
        style = TextStyle(   //to edit and customize the text inside
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Serif
        )
    )
}





//Like plant to favourites
@Composable
fun LikeImage() {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 320.dp, y = 30.dp)
    val imageModifierLike = Modifier
        .size(width = 70.dp, height = 70.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Blue)
    Box(
        modifier = boxModifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.like),
            contentDescription = "Like Image",
            contentScale = ContentScale.FillWidth,
            modifier = imageModifierLike

        )
    }

}


    //This is were we activate the view of the page
    @Preview
    @Composable
    fun GreetingPreview() {
        Plantapp2Theme {
            PlantInfoPage()
        }
    }
