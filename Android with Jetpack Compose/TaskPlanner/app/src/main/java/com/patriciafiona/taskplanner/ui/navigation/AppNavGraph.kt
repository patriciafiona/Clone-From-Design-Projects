package com.patriciafiona.taskplanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patriciafiona.taskplanner.ui.screen.main.bottomNavScreen.CalendarScreen
import com.patriciafiona.taskplanner.ui.screen.main.bottomNavScreen.HomeScreen
import com.patriciafiona.taskplanner.ui.screen.main.bottomNavScreen.ProfileScreen
import com.patriciafiona.taskplanner.ui.screen.main.bottomNavScreen.TaskListScreen
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController, 
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController, taskViewModel = taskViewModel)
        }
        composable(route = BottomBarScreen.TaskList.route) {
            TaskListScreen(navController = navController, taskViewModel = taskViewModel)
        }
        composable(route = BottomBarScreen.Calendar.route) {
            CalendarScreen(navController = navController, taskViewModel = taskViewModel)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}