package com.patriciafiona.plantyshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.plantyshop.ui.screens.DetailScreen
import com.patriciafiona.plantyshop.ui.screens.MainScreen

@Composable
fun NavigationBuilder() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = PlantScreen.MainScreen.route){
        composable(route = PlantScreen.MainScreen.route){
            MainScreen(navController = navigationController)
        }
        composable(route = PlantScreen.DetailScreen.route){
            DetailScreen(navController = navigationController)
        }
    }
}