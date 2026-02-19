package com.patriciafiona.taskplanner.ui.widget.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.patriciafiona.taskplanner.ui.theme.BluePrimary
import java.util.Calendar

@Composable
fun MonthYearPickerDialog(
    initialDate: Calendar,
    onDismiss: () -> Unit,
    onConfirm: (Calendar) -> Unit
) {
    var selectedYear by remember { mutableStateOf(initialDate.get(Calendar.YEAR)) }
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    var selectedMonth by remember { mutableStateOf(months[initialDate.get(Calendar.MONTH)]) }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.8f), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { selectedYear-- }) {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Previous Year",
                        tint = Color.White
                    )
                }
                Text(
                    selectedYear.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
                IconButton(onClick = { selectedYear++ }) {
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = "Next Year",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                months.chunked(3).forEach { rowMonths ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        rowMonths.forEach { month ->
                            TextButton(onClick = { selectedMonth = month }) {
                                Text(
                                    text = month,
                                    color = if (selectedMonth == month) BluePrimary else Color.White,
                                    fontWeight = if (selectedMonth == month) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("Cancel", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = {
                    val calendar = Calendar.getInstance().apply {
                        set(Calendar.YEAR, selectedYear)
                        set(Calendar.MONTH, months.indexOf(selectedMonth))
                    }
                    onConfirm(calendar)
                }) {
                    Text("OK", color = Color.White)
                }
            }
        }
    }
}