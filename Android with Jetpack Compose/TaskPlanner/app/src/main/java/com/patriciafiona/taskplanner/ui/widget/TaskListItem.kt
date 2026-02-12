package com.patriciafiona.taskplanner.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.taskplanner.R
import com.patriciafiona.taskplanner.data.Task
import com.patriciafiona.taskplanner.ui.theme.interFamily
import com.patriciafiona.taskplanner.ui.theme.poppinsFamily

@Composable
fun TaskListItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D))
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    min = 180.dp,
                    max = 210.dp
                )
        ){
            //Background
            Image(
                painter = painterResource(id = R.drawable.gradiend_black_bg),
                contentDescription = "background image",
                modifier = Modifier
                    .matchParentSize()
                    .blur(radius = 10.dp),
                contentScale = ContentScale.FillBounds
            )
            Box(modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.7f)))

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                // Tags
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    task.categories.forEach {
                        Text(it, color = Color.White, fontSize = 12.sp, fontFamily = interFamily)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Title and Description
                Text(
                    text = task.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = poppinsFamily,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = task.description,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontFamily = interFamily,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Bottom row with assignees and edit button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Assignees
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.user_profile),
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .border(BorderStroke(2.dp, Color(0xFF2D2D2D)), CircleShape),
                            contentScale = ContentScale.Crop,
                        )
                        Box(
                            modifier = Modifier
                                .padding(start = 24.dp)
                                .size(32.dp)
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

                    // Edit button
                    IconButton(
                        onClick = { /* TODO: Handle edit */ },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Gray.copy(alpha = 0.2f),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit task")
                    }
                }
            }
        }
    }
}