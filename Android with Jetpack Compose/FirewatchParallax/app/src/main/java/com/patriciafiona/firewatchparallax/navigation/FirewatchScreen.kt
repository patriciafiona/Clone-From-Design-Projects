package com.patriciafiona.firewatchparallax.navigation

sealed class FirewatchScreen(val route: String) {
    object SplashScreen: FirewatchScreen("splash_screen")
    object HomeScreen: FirewatchScreen("home_screen")
    object TowerScreen: FirewatchScreen("tower_screen")
}