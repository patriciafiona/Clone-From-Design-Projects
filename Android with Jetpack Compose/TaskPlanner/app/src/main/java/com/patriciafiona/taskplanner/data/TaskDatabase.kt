package com.patriciafiona.taskplanner.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}