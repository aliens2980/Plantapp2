package com.example.plantapp2.ui.theme

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.plantapp2.AffirmationsApp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector

)
@Composable
fun Menubar() {
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
    ){
        Scaffold(bottomBar ={
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

        ) { innerPadding->
            AffirmationsApp(
                modifier = Modifier.padding(innerPadding),
                navController = rememberNavController(),
            )

        }
}
}