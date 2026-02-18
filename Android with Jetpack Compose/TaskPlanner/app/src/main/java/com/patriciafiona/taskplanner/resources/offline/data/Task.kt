package com.patriciafiona.taskplanner.resources.offline.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.patriciafiona.taskplanner.utils.Converters
import java.util.Date

@Entity(tableName = "tasks")
@TypeConverters(Converters::class)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val startDate: Date,
    val dueDate: Date? = null,
    val isAllDay: Boolean,
    val categories: List<String> = emptyList(),
    val color: Long
)