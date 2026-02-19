package com.patriciafiona.taskplanner.ui.screen.main.bottomNavScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patriciafiona.taskplanner.resources.offline.data.Task
import com.patriciafiona.taskplanner.ui.theme.BluePrimary
import com.patriciafiona.taskplanner.ui.widget.ImageBackground
import com.patriciafiona.taskplanner.ui.widget.RealTimeIndicator
import com.patriciafiona.taskplanner.ui.widget.dialog.AddTaskDialog
import com.patriciafiona.taskplanner.ui.widget.dialog.MonthYearPickerDialog
import com.patriciafiona.taskplanner.ui.widget.item.TaskCalendarItem
import com.patriciafiona.taskplanner.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@SuppressLint("DefaultLocale")
@Composable
fun CalendarScreen(
    navController: NavController,
    taskViewModel: TaskViewModel
) {
    val tasks by taskViewModel.allTasks.collectAsState(initial = emptyList())
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var displayedMonth by remember { mutableStateOf(Calendar.getInstance()) }
    var showAddTaskDialog by remember { mutableStateOf(false) }
    var editingTask by remember { mutableStateOf<Task?>(null) }
    var showMonthYearPicker by remember { mutableStateOf(false) }

    val filteredTasks = tasks.filter { task ->
        val taskCalendar = Calendar.getInstance().apply { time = task.startDate }
        taskCalendar.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR) &&
                taskCalendar.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR)
    }

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

    if (showMonthYearPicker) {
        MonthYearPickerDialog(
            initialDate = displayedMonth,
            onDismiss = { showMonthYearPicker = false },
            onConfirm = {
                displayedMonth = it
                showMonthYearPicker = false
            }
        )
    }

    ImageBackground {
        Scaffold(
            containerColor = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.width(30.dp)) //Empty space
                    Text("Calendar", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Calendar controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            showMonthYearPicker = true
                        }
                    ) {
                        val monthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
                        Text(
                            monthFormat.format(displayedMonth.time),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            tint = Color.White
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                //Day selector
                val daysInMonth = getDaysInMonth(displayedMonth)
                val lazyListState = rememberLazyListState()
                val coroutineScope = rememberCoroutineScope()
                val density = LocalDensity.current
                val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp

                val today = Calendar.getInstance()

                LazyRow(modifier = Modifier.fillMaxWidth(), state = lazyListState) {
                    itemsIndexed(daysInMonth) { index, day ->
                        val cal = Calendar.getInstance().apply { time = day }
                        val isSelected = cal.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR) &&
                                cal.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR)
                        val isToday = cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) &&
                                cal.get(Calendar.YEAR) == today.get(Calendar.YEAR)

                        DayItem(
                            dayName = SimpleDateFormat("E", Locale.getDefault()).format(day),
                            dayOfMonth = cal.get(Calendar.DAY_OF_MONTH),
                            isSelected = isSelected,
                            isToday = isToday
                        ) {
                            selectedDate = cal
                        }
                    }
                }

                LaunchedEffect(selectedDate, daysInMonth){
                    val selectedIndex = daysInMonth.indexOfFirst {
                        val cal = Calendar.getInstance().apply { time = it }
                        cal.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR) &&
                                cal.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR)
                    }
                    if (selectedIndex != -1) {
                        coroutineScope.launch {
                            val itemWidthPx = with(density) { 70.dp.toPx() }
                            val viewportWidthPx = with(density) { screenWidthDp.toPx() }
                            val scrollOffset = (viewportWidthPx / 2) - (itemWidthPx / 2)
                            lazyListState.animateScrollToItem(selectedIndex, -scrollOffset.toInt())
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                //Timeline
                val scrollState = rememberScrollState()
                val hourHeight = 60.dp
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                ) {
                    // Timeline with hour lines
                    Column(modifier = Modifier
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
                    filteredTasks.forEach { task ->
                        TaskCalendarItem(
                            task = task,
                            hourHeight = hourHeight,
                            onEdit = {
                                editingTask = task
                                showAddTaskDialog = true
                            },
                            onDelete = { taskToDelete ->
                                taskViewModel.delete(taskToDelete)
                            }
                        )
                    }

                    //Real-time indicator
                    if (selectedDate.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                        selectedDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
                        RealTimeIndicator(hourHeight = hourHeight)
                    }
                }
            }
        }
    }
}

fun getDaysInMonth(calendar: Calendar): List<Date> {
    val cal = calendar.clone() as Calendar
    cal.set(Calendar.DAY_OF_MONTH, 1)
    val days = mutableListOf<Date>()
    val month = cal.get(Calendar.MONTH)
    while (cal.get(Calendar.MONTH) == month) {
        days.add(cal.time)
        cal.add(Calendar.DAY_OF_MONTH, 1)
    }
    return days
}

@Composable
fun DayItem(
    dayName: String,
    dayOfMonth: Int,
    isSelected: Boolean,
    isToday: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) BluePrimary else Color.Transparent
    val contentColor = if (isSelected || isToday) Color.White else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(70.dp)
            .padding(horizontal = 8.dp) //Outside padding
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .then(
                if (isToday && !isSelected) {
                    Modifier.border(
                        2.dp,
                        Color.White.copy(alpha = 0.3f),
                        RoundedCornerShape(10.dp)
                    )
                } else {
                    Modifier
                }
            )
            .clickable { onClick() }
            .padding(vertical = 8.dp) //Inner padding
    ) {
        Text(dayName, color = contentColor, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            dayOfMonth.toString(),
            color = contentColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
