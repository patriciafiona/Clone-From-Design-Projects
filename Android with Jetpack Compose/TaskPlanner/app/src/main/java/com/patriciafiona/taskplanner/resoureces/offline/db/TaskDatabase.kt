package com.patriciafiona.taskplanner.resoureces.offline.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.patriciafiona.taskplanner.resoureces.offline.data.Task
import com.patriciafiona.taskplanner.resoureces.offline.TaskDao
import com.patriciafiona.taskplanner.utils.Converters

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}