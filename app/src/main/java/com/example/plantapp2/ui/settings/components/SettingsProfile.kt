package com.example.plantapp2.ui.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
