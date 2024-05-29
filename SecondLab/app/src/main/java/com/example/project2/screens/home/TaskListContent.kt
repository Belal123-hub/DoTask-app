package com.example.project2.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.project2.R
import com.example.project2.model.Task
import com.example.project2.screens.home.component.TaskItem
import com.example.project2.ui.theme.black1

@Composable
fun TaskListContent(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    onCreateNewTaskClicked: () -> Unit,
    onTaskClicked: (Task) -> Unit
) {
    Box(modifier = modifier.fillMaxHeight()) {
        LazyColumn(
            modifier = modifier
        ) {
            items(
                items = tasks,
                key = { task -> task.id }
            ) { item ->
                TaskItem(
                    task = item,
                    onTaskClicked = onTaskClicked
                )
            }
        }
        Button(
            onClick = onCreateNewTaskClicked,
            shape = RoundedCornerShape(23.dp),
            colors = ButtonDefaults.buttonColors(black1),
            modifier = Modifier
                .size(width = 56.dp, height = 56.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.shape),
                contentDescription = stringResource(R.string.AddIcon), modifier = Modifier.size(width = 24.dp, height = 24.dp)
                )
        }
    }
}
