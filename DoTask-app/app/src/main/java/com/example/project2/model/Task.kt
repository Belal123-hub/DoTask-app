package com.example.project2.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val priority: TaskPriority,
    val endDate: Long = 0,
    val formattedEndDate: String = ""
)