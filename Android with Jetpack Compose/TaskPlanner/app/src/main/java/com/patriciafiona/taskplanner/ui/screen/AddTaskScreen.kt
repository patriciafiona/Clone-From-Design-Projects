package com.patriciafiona.taskplanner.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.patriciafiona.taskplanner.data.Task
import com.patriciafiona.taskplanner.ui.widget.ImageBackground
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel
import com.patriciafiona.taskplanner.viewmodel.ViewModelFactory

@Composable
fun AddTaskScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: TaskViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf("") }

    ImageBackground {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = categories,
                    onValueChange = { categories = it },
                    label = { Text("Categories (comma-separated)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val task = Task(
                            title = title,
                            description = description,
                            categories = categories.split(",").map { it.trim() },
                            dueDate = System.currentTimeMillis()
                        )
                        viewModel.insert(task)
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Task")
                }
            }
        }
    }
}
