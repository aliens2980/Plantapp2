package com.example.plantapp2.navigation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.plantapp2.ui.theme.FilterAndSearch.FilterScreen
import com.example.plantapp2.ui.theme.FilterAndSearch.MainSearch

@Composable
fun PlantNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_search") {
        composable("main_search") { MainSearch(navController) }
        composable("filter_screen") { FilterScreen(navController) }
    }
}