package com.patriciafiona.taskplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patriciafiona.taskplanner.resources.offline.TaskRepository
import com.patriciafiona.taskplanner.resources.offline.data.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val allTasks: Flow<List<Task>> = repository.allTasks

    fun getTask(id: Long): Flow<Task> {
        return repository.getTask(id)
    }

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
}
