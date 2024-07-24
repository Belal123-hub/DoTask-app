package com.example.project2.dataholder

import androidx.compose.runtime.mutableStateListOf
import com.example.project2.model.Task

object InMemoryTasks {
    val tasks = mutableStateListOf<Task>()

    var selectedTaskId: Int? = null

    val selectedTask: Task
        get() = tasks.first { it.id == selectedTaskId }

    val selectedTaskOrNull: Task?
        get() = tasks.firstOrNull { it.id == selectedTaskId }

    fun updateTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        tasks[index] = task
    }
}