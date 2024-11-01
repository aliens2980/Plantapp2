package com.example.plantapp2.navigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BedView() {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var height by remember { mutableStateOf("40") }
    var width by remember { mutableStateOf("40") }
    val heightInt = height.toIntOrNull() ?: 40
    val widthInt = width.toIntOrNull() ?: 40
    val heightDp = heightInt.dp
    val widthDp = widthInt.dp

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween // Distribute space evenly
                    ) {
                        TextField(
                            value = height,
                            onValueChange = { height = it },
                            label = { Text("height") },
                            modifier = Modifier.weight(1f) // Occupy half the width
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Add some spacing
                        TextField(
                            value = width,
                            onValueChange = { width = it },
                            label = { Text("width") },
                            modifier = Modifier.weight(1f) // Occupy half the width
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .transformable( //Recognize
                        state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
                            scale *= zoomChange
                            rotation += rotationChange
                            offset += offsetChange
                        }
                    )
                    .background(Color.White)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .graphicsLayer {//apply
                            scaleX = scale
                            scaleY = scale
                            rotationZ = rotation
                            translationX = offset.x
                            translationY = offset.y
                        }
                        .drawBehind {
                            drawRect(
                                color = Color.Gray,
                                topLeft = Offset(-3f, -3f),
                                size = size.copy(
                                    width = size.width + 2.dp.toPx(),
                                    height = size.height + 2.dp.toPx()
                                )
                            )
                        }
                        .background(color = Color.LightGray)
                        .padding(all = 2.dp)
                        .height(heightDp)
                        .width(widthDp)
                )
            }
        }
    )
}




/*
@Composable
fun BedView() {
   //hold
   var scale by remember { mutableStateOf(1f) }
   var rotation by remember { mutableStateOf(0f) }
   var offset by remember { mutableStateOf(Offset.Zero) }

   Scaffold {
       modifier = Modifier.padding(it),
       topBar = { TopAppBar(title = { Text("Create your bed here") }) },
       content = {
           // main content
       }
   }

}


   Box(
       modifier = Modifier.transformable( //Recognize
               state = rememberTransformableState {
                   zoomChange, offsetChange, rotationChange ->
                   scale *= zoomChange
                   rotation += rotationChange
                   offset += offsetChange
               }
           ).background(Color.White)
           .fillMaxSize(),
       contentAlignment = Alignment.Center
   ) {
       Box(
           Modifier.graphicsLayer {//apply
               scaleX = scale
               scaleY = scale
               rotationZ = rotation
               translationX = offset.x
               translationY = offset.y
           }.drawBehind {
               drawRect(
                   color = Color.Gray,
                   topLeft = Offset(-3f, -3f),
                   size = size.copy(width = size.width + 2.dp.toPx(), height = size.height + 2.dp.toPx())
               )
           }.background(color = Color.LightGray)
               .padding(all = 2.dp)
               .size(100.dp)
       )
   }
}
*/