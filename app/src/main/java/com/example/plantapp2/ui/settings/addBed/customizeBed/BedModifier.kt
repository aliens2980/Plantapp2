package com.example.plantapp2.ui.settings.addBed.customizeBed


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.plantapp2.ui.settings.addBed.BedCreationViewModel
import com.example.plantapp2.ui.theme.styling.darkGreen

@Composable
fun BedDimensions(
    dimensionsViewModel: BedDimensionsViewModel,
    creationViewModel: BedCreationViewModel
) {
    val bedLength by dimensionsViewModel.bedLength.collectAsState()
    val bedWidth by dimensionsViewModel.bedWidth.collectAsState()
    val cellState by dimensionsViewModel.cellState.collectAsState()
    val context = LocalContext.current // Get the current Context

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Render the buttons for predefined fills
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { dimensionsViewModel.applySelectAll() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = darkGreen,
                )
            ) {
                Text("Select All")
            }
            Button(onClick = { dimensionsViewModel.applyCircleFill() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = darkGreen,
                )
            ) {
                Text("Circle Fill")
            }
            Button(onClick = { dimensionsViewModel.applyLShapeFill() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = darkGreen,
                )
            ) {
                Text("L-Shape Fill")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Render the zoomable grid
        ZoomableFrame {
            BedGrid(
                length = bedLength,
                width = bedWidth,
                cellState = cellState,
                onCellClick = { row, col -> dimensionsViewModel.toggleCell(row, col) }
            )
        }
    }
}






