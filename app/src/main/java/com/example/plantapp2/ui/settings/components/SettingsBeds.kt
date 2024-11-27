package com.example.plantapp2.ui.settings.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantapp2.ui.settings.SettingsViewModel

@Composable
fun SettingsBeds(
    onAddBedClicked: () -> Unit,
    viewModel: SettingsViewModel
) {
    // Collect the list of beds from the ViewModel
    val beds by viewModel.beds.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (beds.isEmpty()) {
            Text(
                text = "You have no bed yet. Create one now!",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Text(
                text = "Your Beds:",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Render each bed
            beds.forEach { bed ->
                BedItem(
                    bedName = bed.name,
                    onDelete = { viewModel.deleteBed(bed) } // Call ViewModel's delete function
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = { onAddBedClicked() }) { // Call onAddBedClicked here
            Text("Add New Bed")
        }
    }
}

@Composable
fun BedItem(
    bedName: String,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = bedName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f) // Let the text take available space
        )
        IconButton(
            onClick = { onDelete() }, // Call the delete function when clicked
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete Bed",
                tint = Color.Red
            )
        }
    }
}

