package com.example.project2.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.project2.model.TodoScreen
import com.example.project2.screens.editTask.EditTaskScreen
import com.example.project2.screens.home.HomeScreen
import com.example.project2.screens.taskDetails.TaskDetailsScreen

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<TodoScreen>(TodoScreen.Home) }

    when (val screen = currentScreen) {
        TodoScreen.Home -> HomeScreen(
            onCreateNewTaskClicked = { currentScreen = TodoScreen.EditTask(task = null) },
            onTaskClicked = { currentScreen = TodoScreen.TaskDetails(task = it) },
        )

        is TodoScreen.TaskDetails -> TaskDetailsScreen(
            task = screen.task,
            onBackClicked = { currentScreen = TodoScreen.Home },
            onEditTaskClicked = { currentScreen = TodoScreen.EditTask(task = screen.task) },
        )

        is TodoScreen.EditTask -> EditTaskScreen(
            task = screen.task,
            onBackClicked = { currentScreen = TodoScreen.Home },
            onSaveClicked = { currentScreen = TodoScreen.Home },
        )
    }
}