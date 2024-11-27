package com.example.plantapp2.ui.settings.addBed.customizeBed

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun ZoomableFrame(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // States for zoom, pan, and rotation
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    // Transformable state
    val transformableState = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange // Update scale
        rotation += rotationChange // Update rotation
        offset += offsetChange // Update offset for panning
    }

    Box(
        modifier = modifier
            .background(Color.LightGray) // Fixed background color
            .fillMaxWidth()
            .height(300.dp), // Constrain height
        contentAlignment = Alignment.Center
    ) {
        // The grid content gets transformed, not the background
        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale.coerceIn(0.5f, 3f) // Clamp scale between 0.5x and 3x
                    scaleY = scale.coerceIn(0.5f, 3f)
                    translationX = offset.x
                    translationY = offset.y
                    rotationZ = rotation
                }
                .transformable(state = transformableState)
        ) {
            content() // The grid content
        }
    }
}



