package com.example.plantapp2.ui.settings.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
        Text(
            text = "Your Beds:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        beds.forEach { bed ->
            Text(
                text = "${bed.name} (${bed.length}x${bed.width} cm)",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onAddBedClicked() }) { // Call onAddBedClicked here
            Text("Add New Bed")
        }
    }
}

