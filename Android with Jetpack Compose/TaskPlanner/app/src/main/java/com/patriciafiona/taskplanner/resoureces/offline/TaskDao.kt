package com.patriciafiona.taskplanner.resoureces.offline

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.patriciafiona.taskplanner.resoureces.offline.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task): Int

    @Delete
    suspend fun delete(task: Task): Int

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTask(id: Int): Flow<Task>

    @Query("SELECT * FROM tasks ORDER BY dueDate DESC")
    fun getAllTasks(): Flow<List<Task>>
}