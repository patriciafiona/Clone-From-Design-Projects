package com.patriciafiona.marioworld.navigation

sealed class MarioScreen (val route: String) {
    object OnboardingScreen: MarioScreen("onboarding_screen")
    object MainScreen: MarioScreen("main_screen")
    object DetailCharacterScreen: MarioScreen("detail_character_screen")
    object ListCharacterScreen: MarioScreen("list_character_screen")
}