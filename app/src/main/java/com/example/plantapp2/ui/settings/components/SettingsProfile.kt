package com.example.plantapp2.ui.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plantapp2.ui.settings.SettingsViewModel

@Composable
fun SettingsProfile(
    viewModel: SettingsViewModel
) {
    val profile by viewModel.settingsProfile.collectAsState()
    var inEditingMode by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(profile.name) }
    var email by remember { mutableStateOf(profile.email) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (!inEditingMode) {
            // View Mode
            Text(
                text = "Name: ${profile.name}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Email: ${profile.email}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { inEditingMode = true }) {
                Text("Edit Profile")
            }
        } else {
            // Edit Mode
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(
                    onClick = {
                        // Save the changes to the ViewModel
                        viewModel.updateProfile(name, email)
                        inEditingMode = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        // Reset the fields and exit editing mode
                        name = profile.name
                        email = profile.email
                        inEditingMode = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}
