package com.patriciafiona.marioworld.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.firewatchparallax.utils.EnterAnimationFadeIn
import com.patriciafiona.marioworld.ui.screen.characterDetail.CharacterDetail
import com.patriciafiona.marioworld.ui.screen.listCharacters.ListCharacters
import com.patriciafiona.marioworld.ui.screen.main.MainScreen
import com.patriciafiona.marioworld.ui.screen.onboarding.OnboardingScreen

@RequiresApi(Build.VERSION_CODES.O)
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
            EnterAnimationFadeIn (durationInMillis = 550) {
                MainScreen(navController = navigationController)
            }
        }

        composable(route = MarioScreen.DetailCharacterScreen.route) { previousBackStackEntry ->
            val data: com.patriciafiona.marioworld.data.entities.Character? = previousBackStackEntry.arguments?.getParcelable("character")
            if (data != null) {
                EnterAnimationFadeIn (durationInMillis = 1550) {
                    CharacterDetail(
                        navController = navigationController,
                        character = data
                    )
                }
            }
        }

        composable(route = MarioScreen.ListCharacterScreen.route) {
            EnterAnimationFadeIn (durationInMillis = 1550) {
                ListCharacters(navController = navigationController)
            }
        }
    }
}