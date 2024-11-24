package com.example.plantapp2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantapp2.ui.theme.Bed
import com.example.plantapp2.ui.theme.CenteredBed
import com.example.plantapp2.ui.theme.PlantInfoPage
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.plantapp2.navigation.components.PlantNavGraph
import com.example.plantapp2.ui.theme.Plantapp2Theme


data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector

)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            Plantapp2Theme {
                CenteredBed(length = 240, width = 360)

                val navController = rememberNavController()
                val item = listOf(
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
                    ),

                    )
                var selectedIremIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        NavigationBar {
                            item.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedIremIndex == index,
                                    onClick = {
                                        selectedIremIndex = index
                                        //navController.navigate(item.title)
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (selectedIremIndex == index) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title
                                        )

                                    }
                                )
                            }
                        }
                    }

                    ) { innerPadding ->
                        when (selectedIremIndex) {
                            0 -> CenteredBed(length = 240, width = 360)
                            1 -> NavHost(
                                navController = navController,
                                startDestination = "affirmations"
                            ) {
                                composable("affirmations") {
                                    AffirmationsApp(
                                        modifier = Modifier.padding(innerPadding),
                                        navController = navController,
                                    )
                                }
                                composable("plantPage") {
                                    PlantInfoPage(
                                        modifier = Modifier.padding(innerPadding),
                                        navController = navController
                                    )
                                }
                            }

                            // Add more cases if needed for other items
                        }
                    }


                }
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Plantapp2Theme {
            CenteredBed(length = 240, width = 360)
        }
    }
} /*
            val navController = rememberNavController()
            PlantNavGraph(navController = navController)
        }
    }
}
*/