package com.example.project2.dataholder

import androidx.compose.runtime.mutableStateListOf
import com.example.project2.model.Task

object InMemoryTasks {
    val tasks = mutableStateListOf<Task>()
}