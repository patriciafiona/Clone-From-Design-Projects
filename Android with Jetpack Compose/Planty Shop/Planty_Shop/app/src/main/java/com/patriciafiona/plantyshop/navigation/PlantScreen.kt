package com.patriciafiona.plantyshop.navigation

sealed class PlantScreen (val route: String){
    object MainScreen: PlantScreen("main_screen")
    object DetailScreen: PlantScreen("detail_screen")
}
