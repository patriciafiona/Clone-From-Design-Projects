package com.patriciafiona.taskplanner.di

import android.content.Context
import androidx.room.Room
import com.patriciafiona.taskplanner.data.TaskDao
import com.patriciafiona.taskplanner.data.TaskDatabase

object AppModule {
    private fun provideTaskDatabase(context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        ).build()
    }

    fun provideTaskDao(context: Context): TaskDao {
        return provideTaskDatabase(context).taskDao()
    }
}