package com.example.project2.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project2.dataholder.InMemoryTasks
import com.example.project2.screens.editTask.EditTaskScreen
import com.example.project2.screens.home.HomeScreen
import com.example.project2.screens.taskDetails.TaskDetailsScreen

enum class Screens {
    Home,
    TaskDetails,
    EditTask
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Home.name) {
        composable(Screens.Home.name) {
            HomeScreen(
                onCreateNewTaskClicked = { navController.navigate(Screens.EditTask.name) },
                onTaskClicked = { task ->
                    InMemoryTasks.selectedTaskId = task.id
                    navController.navigate(Screens.TaskDetails.name)
                },
            )
        }
        composable(Screens.TaskDetails.name) {
            TaskDetailsScreen(
                onBackClicked = { navController.popBackStack() },
                onEditTaskClicked = { task ->
                    InMemoryTasks.selectedTaskId = task.id
                    navController.navigate(Screens.EditTask.name)
                },
            )
        }
        composable(Screens.EditTask.name) {
            EditTaskScreen(
                onBackClicked = { navController.popBackStack() },
                onSaveClicked = { navController.popBackStack() },
            )
        }
    }
}