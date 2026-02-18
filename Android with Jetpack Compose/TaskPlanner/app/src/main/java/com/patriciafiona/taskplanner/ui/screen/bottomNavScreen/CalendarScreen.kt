package com.patriciafiona.taskplanner.ui.screen.bottomNavScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.taskplanner.resources.offline.data.Task
import com.patriciafiona.taskplanner.ui.widget.ImageBackground
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CalendarScreen(
    navController: NavController,
    taskViewModel: TaskViewModel
) {
    val tasks by taskViewModel.allTasks.collectAsState(initial = emptyList())

    ImageBackground {
        Scaffold(
            containerColor = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 36.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.width(30.dp)) //Empty space
                    Text("Calendar", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Calendar
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("February 2026", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown", tint = Color.White)
                }


                Spacer(modifier = Modifier.height(16.dp))

                //Day selector
                val days = listOf(
                    "Sun" to 12, "Mon" to 13, "Tue" to 14, "Wed" to 15,
                    "Thu" to 16, "Fri" to 17, "Sat" to 18
                )
                var selectedDay by remember { mutableStateOf(15) }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    days.forEach { (dayName, dayOfMonth) ->
                        DayItem(dayName = dayName, dayOfMonth = dayOfMonth, isSelected = selectedDay == dayOfMonth) {
                            selectedDay = dayOfMonth
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                //Timeline
                val scrollState = rememberScrollState()
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Timeline with hour lines
                    Column(modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxWidth()
                    ) {
                        for (hour in 0..23) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                            ) {
                                Text(
                                    text = if (hour > 0) String.format("%02d:00", hour) else "",
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .width(50.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(1.dp)
                                        .background(Color.Gray.copy(alpha = 0.5f))
                                )
                            }
                        }
                    }

                    // Tasks
                    val hourHeight = 60.dp
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(start = 58.dp)
                    ) {
                        tasks.forEach { task ->
                            TaskEntry(task = task, hourHeight = hourHeight, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DayItem(dayName: String, dayOfMonth: Int, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Blue else Color.Transparent
    val contentColor = if (isSelected) Color.White else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Text(dayName, color = contentColor, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(dayOfMonth.toString(), color = contentColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TaskEntry(task: Task, hourHeight: Dp, navController: NavController) {
    val calendarStart = Calendar.getInstance().apply { time = task.startDate }
    val calendarEnd = if (task.dueDate != null) Calendar.getInstance().apply { time = task.dueDate } else null

    val startHour = calendarStart.get(Calendar.HOUR_OF_DAY)
    var endHour = calendarEnd?.get(Calendar.HOUR_OF_DAY) ?: (startHour + 1)

    if (task.isAllDay) {
        endHour = startHour + 1
    }

    if (endHour < startHour) { // Handles overnight tasks for the day view
        endHour = 24
    }

    var duration = endHour - startHour
    if (!task.isAllDay && duration == 0) {
        duration = 1 // min 1h duration for non-all-day tasks to be visible
    }

    if (duration <= 0) return

    val height = hourHeight * duration
    val topPadding = hourHeight * startHour

    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val time = if(task.isAllDay) {
        "All Day"
    } else if (task.dueDate != null) {
        "${timeFormat.format(task.startDate)} - ${timeFormat.format(task.dueDate)}"
    } else {
        timeFormat.format(task.startDate)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height - 4.dp)
            .padding(top = topPadding, end = 16.dp)
            .clickable {
                navController.navigate("add_edit_task/${task.id}")
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(task.color).copy(alpha = 0.8f))
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(task.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(task.description, color = Color.LightGray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(time, color = Color.LightGray, fontSize = 12.sp)
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    // Placeholder for avatars
                }
                IconButton(onClick = { navController.navigate("add_edit_task/${task.id}") }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Task", tint = Color.White)
                }
            }
        }
    }
}