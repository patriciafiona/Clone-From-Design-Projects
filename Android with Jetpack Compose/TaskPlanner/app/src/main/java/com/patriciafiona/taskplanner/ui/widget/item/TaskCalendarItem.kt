package com.patriciafiona.taskplanner.ui.widget.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.taskplanner.R
import com.patriciafiona.taskplanner.resources.offline.data.Task
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun TaskCalendarItem(
    task: Task,
    hourHeight: Dp,
    onEdit: (Task) -> Unit
) {
    val calendarStart = Calendar.getInstance().apply { time = task.startDate }
    val calendarEnd = if (task.dueDate != null) Calendar.getInstance().apply { time = task.dueDate } else null

    val startHour = calendarStart.get(Calendar.HOUR_OF_DAY) + calendarStart.get(Calendar.MINUTE) / 60f
    var endHour = calendarEnd?.let { it.get(Calendar.HOUR_OF_DAY) + it.get(Calendar.MINUTE) / 60f } ?: (startHour + 1)

    if (task.isAllDay) {
        endHour = startHour + 1
    }

    if (endHour < startHour) { // Handles overnight tasks for the day view
        endHour = 24f
    }

    var duration = endHour - startHour
    if (!task.isAllDay && duration == 0f) {
        duration = 1f // min 1h duration for non-all-day tasks to be visible
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
            .padding(start = 58.dp, end = 16.dp)
            .offset(y = topPadding)
            .clickable { onEdit(task) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(task.color).copy(alpha = 0.8f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    min = 180.dp
                )
        ) {
            //Background
            Image(
                painter = painterResource(id = R.drawable.gradiend_black_bg),
                contentDescription = "background image",
                modifier = Modifier
                    .matchParentSize()
                    .blur(radius = 10.dp),
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.7f))
            )

            //Content
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .background(Color.White.copy(alpha = 0.3f))
                        .fillMaxHeight()
                )

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .weight(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            task.title,
                            modifier = Modifier.fillMaxWidth(0.5f),
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Box {
                            Image(
                                painter = painterResource(id = R.drawable.user_profile),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .border(BorderStroke(2.dp, Color(0xFF2D2D2D)), CircleShape),
                                contentScale = ContentScale.Crop,
                            )
                            Box(
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .size(20.dp)
                                    .background(Color.White, CircleShape)
                                    .border(BorderStroke(2.dp, Color(0xFF2D2D2D)), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Add Assignee",
                                    tint = Color.Black,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        task.description,
                        color = Color.LightGray,
                        fontSize = 12.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(time, color = Color.LightGray, fontSize = 12.sp)

                        IconButton(
                            modifier = Modifier
                                .size(20.dp),
                            onClick = { onEdit(task) }
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Edit Task",
                                tint = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }
}