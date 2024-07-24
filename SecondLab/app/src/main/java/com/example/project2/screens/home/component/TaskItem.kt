package com.example.project2.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DismissDirection.EndToStart
import androidx.compose.material3.DismissDirection.StartToEnd
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project2.dataholder.InMemoryTasks
import com.example.project2.model.Task
import com.example.project2.model.TaskPriority

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    onTaskClicked: (Task) -> Unit
) {
    val backgroundColor = when (task.priority) {
        TaskPriority.HIGH -> Color(0xFFFF685C)
        TaskPriority.MEDIUM -> Color(0xFFFFD130)
        TaskPriority.LOW -> Color(0xFF6579EA)
        TaskPriority.NORMAL -> Color(0xFF52CC57)
    }

    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                InMemoryTasks.tasks.remove(task)
            }
            true
        }
    )
    SwipeToDismiss(
        modifier = modifier.clickable { onTaskClicked(task) },
        directions = setOf(EndToStart),
        state = dismissState,
        background = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Delete",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
        },
        dismissContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 12.dp, horizontal = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = task.title,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
                    )

                    Text(
                        text = task.description,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal),
                        color = Color.White
                    )
                }
                Checkbox(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    checked = task.isCompleted,
                    onCheckedChange = {
                        InMemoryTasks.updateTask(task.copy(isCompleted = it))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.White,
                        uncheckedColor = Color.White,
                        checkmarkColor = backgroundColor
                    )
                )
            }
        }
    )
}