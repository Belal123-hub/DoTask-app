package com.example.project2.model

sealed class TodoScreen {
    data object Home : TodoScreen()
    data class TaskDetails(val task: Task) : TodoScreen()
    data class EditTask(val task: Task?) : TodoScreen()
}