package com.example.plantapp2.ui.settings.addBed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.plantapp2.ui.settings.addBed.customizeBed.BedDimensionsViewModel

@Composable
fun BedCreationInfo(
    creationViewModel: BedCreationViewModel,
    dimensionsViewModel: BedDimensionsViewModel
) {
    val bedName by creationViewModel.bedName.collectAsState()
    val bedLength by dimensionsViewModel.bedLength.collectAsState()
    val bedWidth by dimensionsViewModel.bedWidth.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Bed Name Input
        TextField(
            value = bedName,
            onValueChange = { creationViewModel.updateBedName(it) },
            placeholder = { Text("Enter bed name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Bed Dimensions Inputs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Length Input
            Column(modifier = Modifier.weight(1f)) {
                Text("Length (cm):")
                TextField(
                    value = bedLength.toString(),
                    onValueChange = {
                        val newLength = it.toIntOrNull()
                        if (newLength != null) {
                            dimensionsViewModel.updateBedLength(newLength)
                        }
                    },
                    placeholder = { Text("Length") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                )
            }

            // Width Input
            Column(modifier = Modifier.weight(1f)) {
                Text("Width (cm):")
                TextField(
                    value = bedWidth.toString(),
                    onValueChange = {
                        val newWidth = it.toIntOrNull()
                        if (newWidth != null) {
                            dimensionsViewModel.updateBedWidth(newWidth)
                        }
                    },
                    placeholder = { Text("Width") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}



