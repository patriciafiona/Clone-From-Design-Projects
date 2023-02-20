package com.patriciafiona.marioworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.firewatchparallax.utils.EnterAnimationFadeIn
import com.patriciafiona.marioworld.ui.screen.main.MainScreen
import com.patriciafiona.marioworld.ui.screen.onboarding.OnboardingScreen

@Composable
fun NavigationBuilder() {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = MarioScreen.OnboardingScreen.route
    ) {
        composable(route = MarioScreen.OnboardingScreen.route) {
            EnterAnimationFadeIn (durationInMillis = 1550) {
                OnboardingScreen(navController = navigationController)
            }
        }

        composable(route = MarioScreen.MainScreen.route) {
            EnterAnimationFadeIn (durationInMillis = 1550) {
                MainScreen(navController = navigationController)
            }
        }
    }
}