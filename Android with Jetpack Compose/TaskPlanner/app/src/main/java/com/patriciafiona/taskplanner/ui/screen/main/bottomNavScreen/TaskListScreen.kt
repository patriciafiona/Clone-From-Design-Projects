package com.patriciafiona.taskplanner.ui.screen.main.bottomNavScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.taskplanner.ui.theme.interFamily
import com.patriciafiona.taskplanner.ui.widget.ImageBackground
import com.patriciafiona.taskplanner.ui.widget.item.TaskListItem
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(
    navController: NavController,
    taskViewModel: TaskViewModel
){
    val tasks by taskViewModel.allTasks.collectAsState(initial = emptyList())

    ImageBackground {
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets.safeDrawing
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 50.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "My Tasks",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = interFamily
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tasks) { task ->
                        TaskListItem(
                            task = task,
                            onEdit = {},
                            onDelete = { taskViewModel.delete(it) },
                        )
                    }
                }
            }
        }
    }
}