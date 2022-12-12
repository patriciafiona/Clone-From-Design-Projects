package com.patriciafiona.plantyshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.plantyshop.data.entity_and_enum.Plant
import com.patriciafiona.plantyshop.ui.screens.detail.DetailScreen
import com.patriciafiona.plantyshop.ui.screens.main.MainScreen
import java.io.File

@Composable
fun NavigationBuilder(getDirectory: File) {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = PlantScreen.MainScreen.route){
        composable(route = PlantScreen.MainScreen.route){
            MainScreen(navController = navigationController, getDirectory = getDirectory)
        }
        composable(route = PlantScreen.DetailScreen.route){ previousBackStackEntry ->
            val plant = previousBackStackEntry.arguments?.getParcelable<Plant>("plant")
            if (plant != null) {
                DetailScreen(
                    navController = navigationController,
                    plant = plant
                )
            }
        }
    }
}