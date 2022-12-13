package com.patriciafiona.plantyshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.cart.CartTab
import com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.home.HomeTab
import com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.plant.PlantTab
import com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.scan.ScanTab
import com.patriciafiona.plantyshop.ui.screens.main.bottomNavigation.setting.SettingTab
import java.io.File


@Composable
fun BottomNavigationBuilder(
    bottomNavigationController: NavHostController,
    navController: NavController,
    getDirectory: File
) {

    NavHost(navController = bottomNavigationController, startDestination = BottomNavigationItem.Home.route) {
        composable(BottomNavigationItem.Home.route) {
            HomeTab(navController = navController, bottomNavController = bottomNavigationController)
        }
        composable(BottomNavigationItem.Plant.route) {
            PlantTab(navController)
        }
        composable(BottomNavigationItem.Scan.route) {
            ScanTab(navController, bottomNavController = bottomNavigationController, getDirectory = getDirectory)
        }
        composable(BottomNavigationItem.Cart.route) {
            CartTab(navController)
        }
        composable(BottomNavigationItem.Setting.route) {
            SettingTab(navController)
        }
    }
}