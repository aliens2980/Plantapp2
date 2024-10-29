package com.example.plantapp2.ui.theme

import androidx.compose.foundation.BorderStroke
import coil.compose.AsyncImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.plantapp2.R


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
        BackgroundImage(url = "background", modifier = Modifier)
        //Other content
        PageTitle(name = "Potato", modifier = Modifier.align(Alignment.Center))
        //Plant photo
        PlantImage(url = "Potato", modifier = Modifier)
        //Plant information box
        InfoText(information = "Information", modifier = Modifier)   //REMEMBER TO LINK TO API DATA HERE BY A VIEWMODEL SCOPE
        //Information image
        InformationImage(url = "info", modifier = Modifier)
        //Watering can image
        WaterCanImage(url = "Watercan", modifier = Modifier)
        //Water can text
        WaterCanText(information = "Information", modifier = Modifier) //REMEMBER TO LINK TO API DATA HERE
        //Sun image
        SunImage(url = "Sun", modifier = Modifier)
        //Sun text
        SunText(information = "Information", modifier = Modifier) //REMEMBER TO LINK TO API DATA HERE
        //Depth image
        DepthImage(url = "depth", modifier = Modifier)
        //Depth text
        DepthText(information = "Information", modifier = Modifier) //REMEMBER TO LINK TO API DATA HERE
        //Grade image
        GradeImage(url = "grade", modifier = Modifier)
        //Grade text
        GradeText(information = "Information", modifier = Modifier) //REMEMBER TO LINK TO API DATA HERE
        //Like image
        LikeImage()
    }
}


@Composable
fun BackgroundImage(url: String, modifier: Modifier) {
    /*
    val imageModifier = Modifier
        .size(width = 411.dp, height = 913.dp)  //here you can change the background photo size
        //.border(BorderStroke(1.dp, Color.Red))
        .background(Color.Blue)

     */
    AsyncImage(
        model = "https://t4.ftcdn.net/jpg/00/14/74/45/360_F_14744561_RJDuXs5eCrpEHMTg3qduWKRy5ExJJc1b.jpg",
        //painter = painterResource(id = R.drawable.potato),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,   //this makes us able to crop the picture into the size we want by .size
        modifier = Modifier
            .size(width = 411.dp, height = 913.dp)

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
fun PlantImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 100.dp, y = 100.dp)
    Box(
        modifier = boxModifier
    ) {
        AsyncImage(
            model = "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg",
            //painter = painterResource(id = R.drawable.potato),
            contentDescription = "Plant Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 200.dp, height = 200.dp)

        )
    }
}


//Information image
@Composable
fun InformationImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 440.dp)
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
    val infoBoxModifier = modifier
        .offset(x = 140.dp, y = 440.dp)
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
fun WaterCanImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 800.dp)
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
fun SunImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 710.dp)
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
fun DepthImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 620.dp)
    Box(
        modifier = boxModifier
    ) {
        AsyncImage(
            model = "https://cdn.create.vista.com/api/media/small/557110558/stock-vector-shovel-icon-white-background-line-style-vector-illustration",
            //painter = painterResource(id = R.drawable.potato),
            contentDescription = "depth Image",
            contentScale = ContentScale.Crop,   //this makes us able to crop the picture into the size we want by .size
            modifier = Modifier
                .size(width = 70.dp, height = 70.dp)

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
fun GradeImage(url: String, modifier: Modifier) {
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 50.dp, y = 530.dp)
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




//Like plant to list of favourites
@Composable
fun LikeImage() {
    var isSelected by remember { mutableStateOf(false) }
    val boxModifier = Modifier   //how to move the box with the potato image around
        .offset(x = 320.dp, y = 30.dp)
    val imageModifierLike = Modifier
        .size(width = 70.dp, height = 70.dp)
    //.border(BorderStroke(1.dp, Color.Black))
    //.background(Color.Transparent)
    Box(
        modifier = boxModifier
            .clickable{
                isSelected = !isSelected
            }
        //.background(if (isSelected) Color.Red else Color.Transparent)
    ) {
        if(isSelected)
            Image(
                painter = painterResource(id = R.drawable.like4),
                contentDescription = "Like Image Filled",
                contentScale = ContentScale.FillWidth,
                modifier = imageModifierLike

            ) else
            Image(
                painter = painterResource(id = R.drawable.like3),
                contentDescription = "Like Image Empty",
                contentScale = ContentScale.FillWidth,
                modifier = imageModifierLike

            )

    }

}
