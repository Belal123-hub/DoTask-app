package com.example.project2.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project2.R
import com.example.project2.dataholder.InMemoryTasks
import com.example.project2.model.Task
import com.example.project2.ui.theme.nunitoFontFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onCreateNewTaskClicked: () -> Unit,
    onTaskClicked: (Task) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(64.dp)
                .padding(start = 4.dp, end = 4.dp, top = 18.dp),
            text = stringResource(R.string.NotForgot),
            fontSize = 24.sp,
            fontFamily = nunitoFontFamily,
            color = Color(color = 0xFF1D1B20)
        )
        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            val tasks = InMemoryTasks.tasks.sortedBy { it.priority.order }

            if (tasks.isEmpty()) {
                EmptyContent(
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                TaskListContent(
                    modifier = Modifier.fillMaxSize(),
                    tasks = tasks,
                    onTaskClicked = onTaskClicked
                )
            }

            IconButton(
                modifier = Modifier
                    .padding(16.dp)
                    .size(56.dp)
                    .background(
                        color = Color(0xFF292929),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .align(Alignment.BottomEnd),
                onClick = onCreateNewTaskClicked,
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null
                )
            }
        }
    }
}
