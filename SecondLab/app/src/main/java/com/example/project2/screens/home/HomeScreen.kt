package com.example.project2.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.project2.R
import com.example.project2.dataholder.InMemoryTasks
import com.example.project2.model.Task

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCreateNewTaskClicked: () -> Unit,
    onTaskClicked: (Task) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.NotForgot),
                        )
                    }
                }, modifier = Modifier
            )
        },
        content = { paddingValues ->
            val tasks = InMemoryTasks.tasks.sortedBy { it.priority.order }
            if (tasks.isEmpty()) {
                EmptyContent(
                    modifier = Modifier.padding(paddingValues),
                    onCreateNewTaskClicked = onCreateNewTaskClicked
                )
            }
            else {
                TaskListContent(
                    modifier = Modifier.padding(paddingValues),
                    tasks = tasks,
                    onCreateNewTaskClicked = onCreateNewTaskClicked,
                    onTaskClicked = onTaskClicked
                )
            }
        }
    )
}
