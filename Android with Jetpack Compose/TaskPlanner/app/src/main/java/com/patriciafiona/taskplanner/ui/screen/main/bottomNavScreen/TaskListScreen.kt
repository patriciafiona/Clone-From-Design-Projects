package com.patriciafiona.taskplanner.ui.screen.main.bottomNavScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.patriciafiona.taskplanner.ui.widget.ImageBackground
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(
    navController: NavController,
    taskViewModel: TaskViewModel
){
    ImageBackground {
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets.safeDrawing
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text("Task List Screen")
            }
        }
    }
}