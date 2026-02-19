package com.patriciafiona.taskplanner.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.patriciafiona.taskplanner.resources.offline.TaskDao
import com.patriciafiona.taskplanner.resources.offline.db.TaskDatabase

object AppModule {
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE tasks ADD COLUMN categories TEXT NOT NULL DEFAULT ''")
        }
    }

    private fun provideTaskDatabase(context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    fun provideTaskDao(context: Context): TaskDao {
        return provideTaskDatabase(context).taskDao()
    }
}