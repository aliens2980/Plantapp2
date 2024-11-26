package com.example.plantapp2.ui.settings.addBed


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Create Your Bed",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Bed creation inputs (name, length, width)
                BedCreationInfo(
                    creationViewModel = creationViewModel,
                    dimensionsViewModel = dimensionsViewModel
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Render the grid using BedDimensions
                BedDimensions(viewModel = dimensionsViewModel)

                Spacer(modifier = Modifier.height(16.dp))

                // Save and Cancel Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            // Sync dimensions from BedDimensionsViewModel to BedCreationViewModel
                            creationViewModel.updateBedLength(dimensionsViewModel.bedLength.value)
                            creationViewModel.updateBedWidth(dimensionsViewModel.bedWidth.value)
                            creationViewModel.saveBed()
                            onSaveBed()
                        }
                    ) {
                        Text("Save Bed")
                    }
                    Button(onClick = { onCancel() }) {
                        Text("Cancel")
                    }
                }
            }
        }
    )
}


