package com.patriciafiona.taskplanner.resoureces.offline.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val dueDate: Long,
    val categories: List<String> = emptyList(),
    val isCompleted: Boolean = false
)