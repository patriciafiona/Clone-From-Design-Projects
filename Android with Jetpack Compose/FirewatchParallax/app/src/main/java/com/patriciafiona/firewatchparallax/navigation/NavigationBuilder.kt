package com.patriciafiona.firewatchparallax.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.firewatchparallax.ui.screen.home.HomeScreen
import com.patriciafiona.firewatchparallax.ui.screen.splash.SplashScreen
import com.patriciafiona.firewatchparallax.ui.screen.tower.TowerScreen
import com.patriciafiona.firewatchparallax.utils.EnterAnimationFadeIn
import com.patriciafiona.firewatchparallax.utils.EnterAnimationSlide

@Composable
fun NavigationBuilder() {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = FirewatchScreen.SplashScreen.route
    ) {
        composable(route = FirewatchScreen.SplashScreen.route) {
            EnterAnimationFadeIn (durationInMillis = 1550) {
                SplashScreen(navController = navigationController)
            }
        }

        composable(route = FirewatchScreen.HomeScreen.route) {
            EnterAnimationSlide {
                HomeScreen(navController = navigationController)
            }
        }

        composable(route = FirewatchScreen.TowerScreen.route) {
            EnterAnimationFadeIn (durationInMillis = 3050)  {
                TowerScreen(navController = navigationController)
            }
        }
    }
}