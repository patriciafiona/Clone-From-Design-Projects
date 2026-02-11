package com.patriciafiona.taskplanner.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    fun getTask(id: Int): Flow<Task> = taskDao.getTask(id)

    suspend fun insert(task: Task): Long {
        return taskDao.insert(task)
    }

    suspend fun update(task: Task): Int {
        return taskDao.update(task)
    }

    suspend fun delete(task: Task): Int {
        return taskDao.delete(task)
    }
}