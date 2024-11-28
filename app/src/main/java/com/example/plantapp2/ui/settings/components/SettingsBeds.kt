package com.example.plantapp2.ui.settings.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val beds by viewModel.beds.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (beds.isEmpty()) {
            Text(
                text = "No beds created yet. Create one now!",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Text(
                text = "Your Beds:",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            beds.forEach { bed ->
                BedItem(
                    bedName = bed.name,
                    onDelete = { viewModel.deleteBed(bed) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = onAddBedClicked) { // Navigate to create bed
            Text("Add New Bed")
        }
    }
}




@Composable
fun BedItem(
    bedName: String,
    onDelete: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

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
            onClick = { showDialog = true }, // Show confirmation dialog
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete Bed",
                tint = Color.Red
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Delete Bed") },
            text = { Text("Are you sure you want to delete this bed? This action cannot be undone.") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onDelete() // Perform the delete action
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

