
package com.patriciafiona.taskplanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.taskplanner.ui.screen.CalendarScreen
import com.patriciafiona.taskplanner.ui.screen.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
        composable("calendar") { CalendarScreen(navController = navController) }
    }
}
