package com.patriciafiona.financeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patriciafiona.financeapp.data.BottomNavItem
import com.patriciafiona.financeapp.ui.view.main.chart.ChartScreen
import com.patriciafiona.financeapp.ui.view.main.home.HomeScreen
import com.patriciafiona.financeapp.ui.view.main.scan.ScanScreen
import com.patriciafiona.financeapp.ui.view.main.user.UserScreen
import com.patriciafiona.financeapp.ui.view.main.wallet.WalletScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Wallet.screen_route) {
            WalletScreen()
        }
        composable(BottomNavItem.Scan.screen_route) {
            ScanScreen()
        }
        composable(BottomNavItem.Chart.screen_route) {
            ChartScreen()
        }
        composable(BottomNavItem.User.screen_route) {
            UserScreen()
        }
    }
}