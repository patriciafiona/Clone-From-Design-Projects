package com.patriciafiona.taskplanner.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patriciafiona.taskplanner.resoureces.offline.data.Task

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    task: Task? = null,
    onDismiss: () -> Unit,
    onConfirm: (Task) -> Unit
) {
    val isEditMode = task != null
    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    val categories = listOf("Work", "Personal", "Shopping", "Health", "Study", "Home", "Finance", "Urgent")
    val selectedCategories = remember { mutableStateOf(task?.categories ?: emptyList()) }
    var isTitleValid by remember { mutableStateOf(true) }
    var isDescriptionValid by remember { mutableStateOf(true) }
    var areCategoriesValid by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isEditMode) "Edit Task" else "Add Task", color = Color.White) },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = !isTitleValid,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    )
                )
                if (!isTitleValid) {
                    Text("Title cannot be empty", color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 4.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = !isDescriptionValid,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    )
                )
                if (!isDescriptionValid) {
                    Text("Description cannot be empty", color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 4.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Categories (Max 3)", modifier = Modifier.padding(bottom = 8.dp), color = Color.White)
                FlowRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    categories.forEach { category ->
                        FilterChip(
                            selected = selectedCategories.value.contains(category),
                            onClick = {
                                val currentCategories = selectedCategories.value.toMutableList()
                                if (currentCategories.contains(category)) {
                                    currentCategories.remove(category)
                                } else {
                                    if (currentCategories.size < 3) {
                                        currentCategories.add(category)
                                    }
                                }
                                selectedCategories.value = currentCategories
                            },
                            label = { Text(category) },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
                if (!areCategoriesValid) {
                    Text("Please select at least one category", color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    isTitleValid = title.isNotBlank()
                    isDescriptionValid = description.isNotBlank()
                    areCategoriesValid = selectedCategories.value.isNotEmpty()

                    if (isTitleValid && isDescriptionValid && areCategoriesValid) {
                        val resultTask = if (isEditMode) {
                            task!!.copy(
                                title = title,
                                description = description,
                                categories = selectedCategories.value
                            )
                        } else {
                            Task(
                                title = title,
                                description = description,
                                categories = selectedCategories.value,
                                dueDate = System.currentTimeMillis()
                            )
                        }
                        onConfirm(resultTask)
                        onDismiss()
                    }
                }
            ) {
                Text(if (isEditMode) "Update Task" else "Add Task")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.White)
            }
        },
        containerColor = Color.Black.copy(alpha = 0.8f)
    )
}