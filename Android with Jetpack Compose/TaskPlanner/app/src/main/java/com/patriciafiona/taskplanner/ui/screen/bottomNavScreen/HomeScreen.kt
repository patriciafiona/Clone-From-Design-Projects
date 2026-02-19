package com.patriciafiona.taskplanner.ui.screen.bottomNavScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.taskplanner.R
import com.patriciafiona.taskplanner.resources.offline.data.Task
import com.patriciafiona.taskplanner.ui.theme.BluePrimary
import com.patriciafiona.taskplanner.ui.widget.dialog.AddTaskDialog
import com.patriciafiona.taskplanner.ui.widget.ImageBackground
import com.patriciafiona.taskplanner.ui.widget.item.TaskListItem
import com.patriciafiona.taskplanner.utils.DateTimeHelper.getCurrentDate
import com.patriciafiona.taskplanner.utils.Greetings.generateGreetings
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, taskViewModel: TaskViewModel) {
    val context = LocalContext.current
//    val viewModel: TaskViewModel = viewModel(
//        factory = ViewModelFactory.getInstance(context)
//    )
    val tasks by taskViewModel.allTasks.collectAsState(initial = emptyList())
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    val categories = listOf("All", "Work", "Personal", "Shopping", "Health", "Study", "Home", "Finance", "Urgent")
    var showAddTaskDialog by remember { mutableStateOf(false) }
    var editingTask by remember { mutableStateOf<Task?>(null) }

    if (showAddTaskDialog) {
        AddTaskDialog(
            task = editingTask,
            onDismiss = { showAddTaskDialog = false },
            onConfirm = { task ->
                if (editingTask == null) {
                    taskViewModel.insert(task)
                } else {
                    taskViewModel.update(task)
                }
                showAddTaskDialog = false
                editingTask = null
            }
        )
    }

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
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // User avatar
                        Image(
                            painter = painterResource(id = R.drawable.user_profile),
                            contentDescription = "User Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Patricia Fiona",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(generateGreetings(),
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                    Row {
                        IconButton(
                            onClick = { /* TODO */ },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Black.copy(alpha = 0.4f),
                                contentColor = Color.White
                            )
                        ) {
                            Icon(Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = { navController.navigate("calendar") },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Black.copy(alpha = 0.4f),
                                contentColor = Color.White
                            )
                        ) {
                            Icon(Icons.Default.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Daily Productivity
                Text(
                    "Daily Productivity",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    getCurrentDate(),
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Priority Task Progress
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = BluePrimary)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Priority Task Progress",
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                        Text("12/20 is Completed", color = Color.Black, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = 0.65f,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White,
                            trackColor = Color.DarkGray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // My Task
                Text("My Task", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { category ->
                        val isSelected = (selectedCategory == category) || (selectedCategory == null && category == "All")
                        Button(
                            onClick = {
                                selectedCategory = if (category == "All") {
                                    null
                                } else if (selectedCategory == category) {
                                    null
                                } else {
                                    category
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = if (isSelected) Color.White else Color(0xFF222222))
                        ) {
                            Text(category, color = if (isSelected) Color.Black else Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    // Today's Task
                    Text("Today's Task", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            "See All",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }

                LazyColumn(
                    contentPadding = PaddingValues(bottom = 100.dp),
                ) {
                    items(tasks.filter { selectedCategory == null || it.categories.contains(selectedCategory!!) }) { task ->
                        TaskListItem(
                            task = task,
                            onEdit = {
                                editingTask = task
                                showAddTaskDialog = true
                            },
                            onDelete = { taskViewModel.delete(it) }
                        )
                    }
                }
            }
        }
    }
}