package com.example.plantapp2.navigation.components

import android.view.Surface
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantapp2.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambdaInstance
import androidx.compose.runtime.internal.rememberComposableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.pm.ShortcutInfoCompat


data class CarouselItem(
    val id: Int,
    val text: String,
    val imageResource: Int,
    val contentDescription: String
)



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PlantPickerContent() {


    var plantInBed by remember { mutableStateOf<CarouselItem?>(null) }
    var selectedPlant by remember { mutableStateOf<CarouselItem?>(null) }

    fun updateCurrentItem(newItem: CarouselItem) {
        println("Updating current item to: ${newItem.text}")
        plantInBed = selectedPlant
    }

    val item = remember {
        listOf( //placeholders
            // Adding "Krydderurter" plants
            CarouselItem(5, "Purløg", R.drawable.psychiatry_24px, "Size: small, Thrives with: timian, basilikum, persille"),
            CarouselItem(6, "Timian", R.drawable.ic_launcher_foreground, "Size: small, Thrives with: purløg, basilikum, persille"),
            CarouselItem(7, "Basilikum", R.drawable.ic_launcher_background, "Size: small, Thrives with: purløg, timian, persille"),
            CarouselItem(8, "Persille", R.drawable.psychiatry_24px, "Size: small, Thrives with: purløg, timian, basilikum"),

            // Other plants
            CarouselItem(9, "Chili", R.drawable.chiliplant, "Size: medium, Thrives with: tomat, basilikum"),
            CarouselItem(10, "Bælgfrugter", R.drawable.bealgfrugt, "Size: medium, Thrives with: gulerødder, tomat"),
            CarouselItem(11, "Tomat", R.drawable.psychiatry_24px, "Size: medium, Thrives with: basilikum, chili"),
            CarouselItem(12, "Gulerødder", R.drawable.psychiatry_24px, "Size: medium, Thrives with: bælgfrugter, purløg"),
            CarouselItem(13, "Kartoffel", R.drawable.kartoffel, "Size: medium, Thrives with: bælgfrugter, purløg")

        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Box( // Can use linking to match box (bottom) with carousels (top) beginning.
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.White)
                .padding(bottom = 200.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .background(Color.LightGray)
                    .align(Alignment.Center)

                    .clickable {
                        selectedPlant?.let { updateCurrentItem(it) }
                        println("Box clicked:")
                    },
            ) {
                if (plantInBed != null) {
                    selectedPlant?.let { painterResource(id = it.imageResource) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "image.contentDescription",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                } else {
                    Box( //placeholder

                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Cyan)
                            .clickable {

                                println("Hej")
                            }
                    )
                }
            }
        }

        HorizontalUncontainedCarousel(
            state = rememberCarouselState {
                item.count()
                },
            itemWidth = 100.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(start = 12.dp),

            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 12.dp, bottom = 12.dp)
        ) { index ->
            val value = item[index]

            Column (
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .maskClip(shape = MaterialTheme.shapes.large)
                    .background(Color.White)
                    .clickable {
                        updateCurrentItem(item[index]) // Update the item based on index or any custom logic
                        println("Item clicked: ${value.text}")
                    },
                verticalArrangement = Arrangement.Bottom, // Arrange content at the bottom

            ) {
                Image(
                    painter = painterResource(id = value.imageResource),
                    contentDescription = value.contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(all = 4.dp)
                        .maskClip(shape = MaterialTheme.shapes.extraLarge)
                        .background(color = Color.White)
                        .pointerInput(Unit) {
                            // Handle click events
                        }
                )
                Text(
                    text = value.text,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 4.dp)

                )
            }
        }
    }
}



@Preview (showBackground = true)
@Composable
fun PlantPickerContentPreview(){
    PlantPickerContent()
}