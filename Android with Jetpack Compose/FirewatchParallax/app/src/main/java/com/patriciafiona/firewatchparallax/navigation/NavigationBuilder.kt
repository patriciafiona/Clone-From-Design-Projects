package com.patriciafiona.firewatchparallax.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.firewatchparallax.ui.screen.home.HomeScreen
import com.patriciafiona.firewatchparallax.ui.screen.splash.SplashScreen

@Composable
fun NavigationBuilder() {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = FirewatchScreen.SplashScreen.route
    ) {
        composable(
            route = FirewatchScreen.SplashScreen.route
        ) {
            SplashScreen(navController = navigationController)
        }

        composable(
            route = FirewatchScreen.HomeScreen.route
        ) {
            HomeScreen(navController = navigationController)
        }
    }
}