package com.patriciafiona.taskplanner.resources.offline

import com.patriciafiona.taskplanner.resources.offline.data.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    fun getTask(id: Long): Flow<Task> {
        return taskDao.getTask(id)
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    companion object {
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(taskDao: TaskDao): TaskRepository =
            instance ?: synchronized(this) {
                instance ?: TaskRepository(taskDao).also { instance = it }
            }
    }
}