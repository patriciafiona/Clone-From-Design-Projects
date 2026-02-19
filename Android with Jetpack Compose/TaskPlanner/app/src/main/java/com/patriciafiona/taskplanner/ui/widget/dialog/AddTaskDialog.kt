package com.patriciafiona.taskplanner.ui.widget.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.patriciafiona.taskplanner.resources.offline.data.Task
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
    var isAllDay by remember { mutableStateOf(task?.isAllDay ?: false) }
    var startDate by remember { mutableStateOf(task?.startDate ?: Date()) }
    var dueDate by remember { mutableStateOf(task?.dueDate) }

    var isTitleValid by remember { mutableStateOf(true) }
    var isDescriptionValid by remember { mutableStateOf(true) }
    var areCategoriesValid by remember { mutableStateOf(true) }
    var isDateValid by remember { mutableStateOf(true) }

    val context = LocalContext.current

    val dateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    var showStartDatePicker by remember { mutableStateOf(false) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showDueDatePicker by remember { mutableStateOf(false) }
    var showDueTimePicker by remember { mutableStateOf(false) }

    LaunchedEffect(startDate) {
        if (dueDate != null && dueDate!!.before(startDate)) {
            dueDate = startDate
        }
    }

    if (showStartDatePicker) {
        val calendar = Calendar.getInstance().apply { time = startDate }
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val newDate = Calendar.getInstance().apply {
                    time = startDate
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                startDate = newDate.time
                showStartDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showStartTimePicker) {
        val calendar = Calendar.getInstance().apply { time = startDate }
        TimePickerDialog(
            context,
            { _, hour, minute ->
                val newDate = Calendar.getInstance().apply {
                    time = startDate
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, minute)
                }
                startDate = newDate.time
                showStartTimePicker = false
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    if (showDueDatePicker) {
        val calendar = Calendar.getInstance().apply { time = dueDate ?: startDate }
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val newDate = Calendar.getInstance().apply {
                    time = dueDate ?: startDate
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                dueDate = newDate.time
                showDueDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = startDate.time
        datePickerDialog.show()
    }

    if (showDueTimePicker) {
        val calendar = Calendar.getInstance().apply { time = dueDate ?: startDate }
        TimePickerDialog(
            context,
            { _, hour, minute ->
                val newDueDate = Calendar.getInstance().apply {
                    time = dueDate ?: startDate
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, minute)
                }

                if (newDueDate.time.before(startDate)) {
                    isDateValid = false
                    Toast.makeText(context, "Due time cannot be before start time", Toast.LENGTH_SHORT).show()
                } else {
                    isDateValid = true
                    dueDate = newDueDate.time
                }
                showDueTimePicker = false
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("All-day", color = Color.White)
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = isAllDay,
                        onCheckedChange = { isAllDay = it }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                if(isAllDay){
                    Text("Start Date", color = Color.White)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextButton(onClick = { showStartDatePicker = true }) {
                            Text(dateFormat.format(startDate), color = Color.White)
                        }
                    }
                }else {
                    Text("Start Date and Time", color = Color.White)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextButton(onClick = { showStartDatePicker = true }) {
                            Text(dateFormat.format(startDate), color = Color.White)
                        }
                        TextButton(onClick = { showStartTimePicker = true }) {
                            Text(timeFormat.format(startDate), color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Due Date and Time", color = Color.White)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextButton(onClick = { showDueDatePicker = true }) {
                            Text(if(dueDate != null) dateFormat.format(dueDate!!) else "Select Date", color = Color.White)
                        }
                        TextButton(onClick = { showDueTimePicker = true }) {
                            Text(if(dueDate != null) timeFormat.format(dueDate!!) else "Select Time", color = Color.White)
                        }
                    }
                    if (!isDateValid) {
                        Text("Due date cannot be before start date.", color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                    }
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
                    isDateValid = dueDate == null || !dueDate!!.before(startDate)

                    if (isTitleValid && isDescriptionValid && areCategoriesValid && isDateValid) {
                        val resultTask = if (isEditMode) {
                            task!!.copy(
                                title = title,
                                description = description,
                                categories = selectedCategories.value,
                                isAllDay = isAllDay,
                                startDate = startDate,
                                dueDate = if (isAllDay) null else dueDate
                            )
                        } else {
                            Task(
                                title = title,
                                description = description,
                                startDate = startDate,
                                dueDate = if(isAllDay) null else dueDate,
                                isAllDay = isAllDay,
                                categories = selectedCategories.value,
                                color = 0xFF000000
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