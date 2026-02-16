package com.patriciafiona.taskplanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patriciafiona.taskplanner.ui.screen.bottomNavScreen.CalendarScreen
import com.patriciafiona.taskplanner.ui.screen.bottomNavScreen.HomeScreen
import com.patriciafiona.taskplanner.ui.screen.bottomNavScreen.ProfileScreen
import com.patriciafiona.taskplanner.ui.screen.bottomNavScreen.TaskListScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.TaskList.route) {
            TaskListScreen()
        }
        composable(route = BottomBarScreen.Calendar.route) {
            CalendarScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}
