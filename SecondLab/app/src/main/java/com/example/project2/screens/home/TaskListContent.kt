package com.example.project2.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project2.model.Task
import com.example.project2.screens.home.component.TaskItem
import com.example.project2.ui.theme.nunitoFontFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskListContent(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    onTaskClicked: (Task) -> Unit
) {
    Column {
        Text(
            text = "Tasks",
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            color = Color.Black,
            fontFamily = nunitoFontFamily
            )

        LazyColumn(modifier = modifier) {
            items(
                items = tasks,
                key = { task -> task.id }
            ) { item ->
                TaskItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .animateItemPlacement(),
                    task = item,
                    onTaskClicked = onTaskClicked
                )
            }
        }
    }
}
