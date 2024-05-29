package com.example.project2.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
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

@Composable
fun TaskItem(
    task: Task,
    onTaskClicked: (Task) -> Unit
) {
    val backgroundColor = when (task.priority) {
        TaskPriority.HIGH -> Color.Red
        TaskPriority.MEDIUM -> Color.Yellow
        TaskPriority.LOW -> Color.Green
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .background(backgroundColor)
            .clickable { onTaskClicked(task) }
    ) {
        Text(
            text = task.title,
            modifier = Modifier.weight(1f),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(if (task.isCompleted) Color.Green else Color.Transparent)
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = {
                    InMemoryTasks.tasks.remove(task)
                    val updatedTask = task.copy(isCompleted = it)
                    InMemoryTasks.tasks.add(updatedTask)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Green,
                    uncheckedColor = Color.Transparent
                )
            )
        }
    }
}