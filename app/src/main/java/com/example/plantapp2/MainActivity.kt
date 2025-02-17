package com.example.plantapp2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantapp2.data.BottomNavItem
import com.example.plantapp2.ui.settings.SettingsMainScreen
import com.example.plantapp2.ui.settings.addBed.MainBedCreationScreen
import com.example.plantapp2.ui.theme.bed.CenteredBed
import com.example.plantapp2.ui.theme.plantPage.PlantInfoPage
import com.example.plantapp2.ui.theme.scrollablePlantList.ScrollablePlantList
import com.example.plantapp2.ui.theme.styling.Plantapp2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Plantapp2Theme {
                val navController = rememberNavController()
                val items = listOf(
                    BottomNavItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home
                    ),
                    BottomNavItem(
                        title = "Search",
                        selectedIcon = Icons.Filled.Search,
                        unselectedIcon = Icons.Outlined.Search
                    ),
                    BottomNavItem(
                        title = "Settings",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings
                    )
                )
                var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color(0xFFDAD7CD)
                        ) {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        // Navigate to the appropriate screen
                                        when (index) {
                                            0 -> navController.navigate("centeredBed") {
                                                popUpTo("centeredBed") { inclusive = false }
                                            }
                                            1 -> navController.navigate("affirmations") {
                                                popUpTo("affirmations") { inclusive = false }
                                            }
                                            2 -> navController.navigate("settings") {
                                                popUpTo("settings") { inclusive = false }
                                            }
                                        }
                                    },
                                    label = { Text(text = item.title) },
                                    icon = {
                                        Icon(
                                            imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "centeredBed", // Static start destination
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFDAD7CD))
                            .padding(innerPadding)
                    ) {
                        composable("centeredBed") {
                            CenteredBed(length = 240, width = 360)
                        }
                        composable("affirmations") {
                            ScrollablePlantList(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }

                        composable("plantPage/{plantName}") { backStackEntry ->
                            val plantName = backStackEntry.arguments?.getString("plantName") ?: "Unknown"
                            PlantInfoPage(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController,
                                getPlantName = plantName
                            )
                        }
                        composable("settings") {
                            SettingsMainScreen(
                                context = LocalContext.current,
                                onViewBeds = { navController.navigate("viewBeds") },
                                onAddBedClicked = { navController.navigate("createBed") } // Navigate to create bed
                            )
                        }
                        composable("createBed") {
                            MainBedCreationScreen(
                                onSaveBed = { navController.navigate("settings") }, // Return to settings after saving
                                onCancel = { navController.popBackStack() }  // Return to settings on cancel
                            )
                        }
                    }
                }
            }
        }
    }
}

