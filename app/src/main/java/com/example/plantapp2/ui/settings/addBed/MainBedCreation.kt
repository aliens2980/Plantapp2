package com.example.plantapp2.ui.settings.addBed


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.plantapp2.ui.settings.addBed.customizeBed.BedDimensions
import com.example.plantapp2.ui.settings.addBed.customizeBed.BedDimensionsViewModel

@Composable
fun MainBedCreationScreen(
    creationViewModel: BedCreationViewModel = BedCreationViewModel(),
    dimensionsViewModel: BedDimensionsViewModel = BedDimensionsViewModel(),
    onSaveBed: () -> Unit,
    onCancel: () -> Unit
) {
    // Retrieve the context from the Composable scope
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp) // Reduced padding
            ) {
                // Title
                Text(
                    text = "Create Your Bed",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp) // Reduced padding
                )

                // Bed creation inputs (name, length, width)
                BedCreationInfo(
                    creationViewModel = creationViewModel,
                    dimensionsViewModel = dimensionsViewModel
                )

                Spacer(modifier = Modifier.height(8.dp)) // Reduced spacing

                // Render the grid using BedDimensions
                BedDimensions(
                    dimensionsViewModel = dimensionsViewModel,
                    creationViewModel = creationViewModel
                )

                Spacer(modifier = Modifier.height(8.dp)) // Reduced spacing

                // Save and Cancel Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween // Spread buttons evenly
                ) {
                    Button(
                        onClick = {
                            // Sync dimensions from BedDimensionsViewModel to BedCreationViewModel
                            creationViewModel.updateBedLength(dimensionsViewModel.bedLength.value)
                            creationViewModel.updateBedWidth(dimensionsViewModel.bedWidth.value)

                            // Extract selected cells from BedDimensionsViewModel
                            val selectedCells = dimensionsViewModel.getSelectedCells()

                            // Save the bed using the provided context
                            try {
                                creationViewModel.saveBed(context, selectedCells)
                                onSaveBed() // Navigate back or execute further action
                            } catch (e: Exception) {
                                println("Error saving bed: ${e.message}")
                            }
                        },
                        modifier = Modifier.weight(1f) // Equal button size
                    ) {
                        Text("Save")
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Spacing between buttons
                    Button(
                        onClick = { onCancel() },
                        modifier = Modifier.weight(1f) // Equal button size
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    )
}




