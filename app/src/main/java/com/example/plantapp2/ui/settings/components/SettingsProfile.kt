package com.example.plantapp2.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantapp2.ui.settings.SettingsViewModel

@Composable
fun SettingsProfile(
    name: String,
    email: String? = null // Optional email field
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Profile Name: $name",
            style = MaterialTheme.typography.bodyLarge
        )
        email?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Email: $it",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
