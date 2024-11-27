package com.example.plantapp2.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantapp2.ui.settings.components.SettingsBeds
import com.example.plantapp2.ui.settings.components.SettingsProfile



@Composable
fun SettingsMainScreen(
    onProfileEdit: () -> Unit = {},
    onViewBeds: () -> Unit, // Pass navigation callback
    viewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var isProfileExpanded by remember { mutableStateOf(false) }
    var isBedsExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF5F5F5))
    ) {
        // Profile Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isProfileExpanded = !isProfileExpanded }
                    .padding(16.dp)
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            if (isProfileExpanded) {
                SettingsProfile(viewModel = viewModel)
            }
        }

        //Spacer(modifier = Modifier.height(16.dp))

        // Beds Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isBedsExpanded = !isBedsExpanded }
                    .padding(16.dp)
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Beds",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            if (isBedsExpanded) {
                SettingsBeds(
                    onAddBedClicked = onViewBeds, // Pass navigation callback here
                    viewModel = viewModel
                )
            }
        }
    }
}




