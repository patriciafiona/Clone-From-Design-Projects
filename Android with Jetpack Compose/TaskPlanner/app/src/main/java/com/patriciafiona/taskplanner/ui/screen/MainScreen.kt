package com.patriciafiona.taskplanner.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.patriciafiona.taskplanner.ui.navigation.AppNavGraph
import com.patriciafiona.taskplanner.ui.theme.BluePrimary
import com.patriciafiona.taskplanner.ui.widget.AddTaskDialog
import com.patriciafiona.taskplanner.ui.widget.customShadow
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    var showAddTaskDialog by remember { mutableStateOf(false) }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    if (showAddTaskDialog) {
        AddTaskDialog(
            task = null,
            onDismiss = { showAddTaskDialog = false },
            onConfirm = { task ->
                viewModel.insert(task)
                showAddTaskDialog = false
            }
        )
    }

    Scaffold(
        bottomBar = { BottomBar(navController = navController, onAddTaskClick = { showAddTaskDialog = true }) }
    ) { 
        AppNavGraph(
            navController = navController,
            taskViewModel = viewModel
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController, onAddTaskClick: () -> Unit) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .navigationBarsPadding()
            .padding(bottom = 24.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.8f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val homeSelected = currentDestination?.hierarchy?.any { it.route == "home" } == true
            IconButton(onClick = { 
                navController.navigate("home") {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    tint = if (homeSelected) BluePrimary else Color.White
                )
            }
            val taskSelected = currentDestination?.hierarchy?.any { it.route == "task_list" } == true
            IconButton(onClick = { 
                navController.navigate("task_list") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Icon(
                    Icons.Default.List,
                    contentDescription = "Task",
                    tint = if (taskSelected) BluePrimary else Color.White
                )
            }

            IconButton(
                onClick = onAddTaskClick,
                modifier = Modifier
                    .customShadow(
                        color = BluePrimary,
                        blurRadius = 20.dp
                    )
                    .size(56.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = BluePrimary,
                )
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Task",
                    tint = Color.Black
                )
            }

            val calendarSelected = currentDestination?.hierarchy?.any { it.route == "calendar" } == true
            IconButton(onClick = { 
                navController.navigate("calendar") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    tint = if (calendarSelected) BluePrimary else Color.White
                )
            }
            val profileSelected = currentDestination?.hierarchy?.any { it.route == "profile" } == true
            IconButton(onClick = { 
                navController.navigate("profile") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
             }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = if (profileSelected) BluePrimary else Color.White
                )
            }
        }
    }
}
