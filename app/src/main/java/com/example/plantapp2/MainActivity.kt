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
        //Plant information box
        InfoText(information = "Information", modifier = Modifier)   //REMEMBER TO LINK TO API DATA HERE BY A VIEWMODEL SCOPE
    }
}


@Composable
fun BackgroundImage() {
    val imageModifier = Modifier
        .size(width = 400.dp, height = 900.dp)  //here you can change the background photo size
        .border(BorderStroke(1.dp, Color.Red))
        .background(Color.Blue)
    Image(
        painter = painterResource(id = R.drawable.daisy_background),
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






    //This is were we activate the view of the page
    @Preview
    @Composable
    fun GreetingPreview() {
        Plantapp2Theme {
            PlantInfoPage()
        }
    }
