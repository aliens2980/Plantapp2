package com.example.plantapp2.ui.settings.components


import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantapp2.mvvm.home.SavedBedsViewModelFactory
import com.example.plantapp2.ui.home.SavedBedsViewModel
import com.example.plantapp2.ui.settings.SettingsViewModel
import com.example.plantapp2.ui.theme.styling.darkGreen

@Composable
fun SettingsBeds(
    onAddBedClicked: () -> Unit,
    viewModel: SettingsViewModel
) {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("bed_preferences", Context.MODE_PRIVATE)

    val SavedBedsViewModel: SavedBedsViewModel = viewModel(factory = SavedBedsViewModelFactory(context))
    val beds by viewModel.beds.collectAsState()
    var selectedBedName by remember { mutableStateOf("") }


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
                    onDelete = { viewModel.deleteBed(bed) },
                    onSelectApply = {
                        bed.name
                    },
                    context = context
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
    onDelete: () -> Unit,
    onSelectApply: (String) -> Unit,
    context: Context
) {
    var rememberBedName by remember { mutableStateOf(false) }

    // Get the current selected bed from shared preferences
    LaunchedEffect(bedName) {
        val sharedPrefs = context.getSharedPreferences("bed_preferences", Context.MODE_PRIVATE)
        val currentBed = sharedPrefs.getString("current_bed", null) // Retrieve the selected bed name
        rememberBedName = currentBed == bedName // Check if this bed is the selected one
    }

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
        var selectedBedName by rememberSaveable { mutableStateOf("") }

        // Select or unselect the bed
        IconButton(
            onClick = {
                val sharedPrefs = context.getSharedPreferences("bed_preferences", Context.MODE_PRIVATE)
                val editor = sharedPrefs.edit()

                if (rememberBedName) {
                    // If this bed is already selected, remove it from favorites (unselect it)
                    editor.remove("current_bed")
                } else {
                    // If this bed is not selected, set this one as the favorite and remove the previous selection if it exists
                    editor.putString("current_bed", bedName)
                }
                editor.apply()

                rememberBedName = !rememberBedName // Toggle the selection state

                selectedBedName = bedName
                onSelectApply(selectedBedName) // Notify parent of the selected bed
            }, modifier = Modifier.size(50.dp)
        ) {
            Icon(
                imageVector = if (rememberBedName) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                contentDescription = "selected",
                tint = if (rememberBedName) darkGreen else Color.Gray // Change color based on selection state
            )
        }

        // Delete Bed
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

    // Confirmation dialog for deleting the bed
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