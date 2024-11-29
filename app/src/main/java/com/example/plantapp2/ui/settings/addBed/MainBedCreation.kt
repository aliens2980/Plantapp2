package com.example.plantapp2.ui.settings.addBed


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.plantapp2.ui.settings.addBed.customizeBed.BedDimensions
import com.example.plantapp2.ui.settings.addBed.customizeBed.BedDimensionsViewModel
import com.example.plantapp2.ui.theme.styling.darkGreen

@Composable
fun MainBedCreationScreen(
    creationViewModel: BedCreationViewModel = BedCreationViewModel(),
    dimensionsViewModel: BedDimensionsViewModel = BedDimensionsViewModel(),
    onSaveBed: () -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp) // Reduced padding
            ) {
                Text(
                    text = "Create Your Bed",
                    style = MaterialTheme.typography.headlineMedium
                )

                BedCreationInfo(
                    creationViewModel = creationViewModel,
                    dimensionsViewModel = dimensionsViewModel
                )

                BedDimensions(
                    dimensionsViewModel = dimensionsViewModel,
                    creationViewModel = creationViewModel,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            // Sync dimensions and extract selected cells
                            creationViewModel.updateBedLength(dimensionsViewModel.bedLength.value)
                            creationViewModel.updateBedWidth(dimensionsViewModel.bedWidth.value)
                            val selectedCells = dimensionsViewModel.getSelectedCells()

                            try {
                                Log.d(
                                    "BedCreation",
                                    "Saving bed: Name=${creationViewModel.bedName.value}, " +
                                            "Length=${creationViewModel.bedLength.value}, " +
                                            "Width=${creationViewModel.bedWidth.value}, " +
                                            "SelectedCells=$selectedCells"
                                )
                                creationViewModel.saveBed(context, selectedCells)
                                Log.d("BedCreation", "Bed saved successfully!")
                                onSaveBed()
                            } catch (e: Exception) {
                                Log.e("BedCreation", "Error saving bed: ${e.message}", e)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = darkGreen
                        )
                    ) {
                        Text("Save Bed")
                    }
                    Button(onClick = { onCancel()},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = darkGreen
                        )
                        ) {
                        Text("Cancel")
                    }
                }
            }
        }
    )
}





