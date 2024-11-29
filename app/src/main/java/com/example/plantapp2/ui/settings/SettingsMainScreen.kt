package com.example.plantapp2.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantapp2.ui.settings.components.SettingsBeds
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.plantapp2.data.localData.SettingsProfile
import com.example.plantapp2.ui.settings.addBed.MainBedCreationScreen
import com.example.plantapp2.ui.theme.styling.darkGreen

@Composable
fun SettingsMainScreen(
    onViewBeds: () -> Unit, // Callback for viewing beds
    context: Context,
    onAddBedClicked: () -> Unit // Callback for navigating to bed creation
) {
    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(context)
    )
    var isProfileExpanded by remember { mutableStateOf(false) }
    var isBedsExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Profile Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isProfileExpanded = !isProfileExpanded }
                    .padding(16.dp)
                    .background(color = darkGreen)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }
            if (isProfileExpanded) {
                SettingsProfile(
                    name = "John Doe", // Replace with actual profile data if needed
                    email = "john.doe@example.com" // Replace with actual profile data if needed
                )
            }
        }

        // Beds Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isBedsExpanded = !isBedsExpanded }
                    .padding(16.dp)
                    .background(color = darkGreen)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Beds",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }
            if (isBedsExpanded) {
                SettingsBeds(
                    onAddBedClicked = onAddBedClicked, // Navigate to bed creation
                    viewModel = viewModel
                )
            }
        }
    }
}
