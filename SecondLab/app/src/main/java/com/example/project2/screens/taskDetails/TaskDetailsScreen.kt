package com.example.project2.screens.taskDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project2.R
import com.example.project2.model.Task
import com.example.project2.screens.editTask.component.toFormattedString
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    task: Task,
    onEditTaskClicked: (Task) -> Unit,
    onBackClicked: () -> Unit
) {
    val formattedEndDate = Date(task.endDate).toFormattedString()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.Task),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },

                modifier = Modifier.fillMaxWidth()
            )
        },
        content = { paddingValues ->
            Column {
                Row(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                ) {
                    Text(text = task.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    IconButton(onClick = { onEditTaskClicked.invoke(task) },modifier= Modifier
                        .fillMaxWidth()
                        .padding(start = 200.dp)
                        .width(100.dp)
                        .height(100.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.edit_24px),
                            contentDescription = stringResource(R.string.EditTask),modifier= Modifier
                                .width(15.dp)
                                .height(15.dp)

                        )
                    }
                }
                Text(
                    text = stringResource(R.string.completed), modifier = Modifier.padding(start = 300.dp),
                    color= if (task.isCompleted) {
                        Color.Green
                    } else Color.Red
                )

                Spacer(modifier = Modifier.heightIn(20.dp))
                Text(text = task.description)

                Spacer(modifier = Modifier.heightIn(20.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(R.drawable.query_builder_24px_outlined),
                        contentDescription = stringResource(R.string.TimeIcon)
                    )
                    Text(
                        text = stringResource(R.string.before, formattedEndDate),
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .size(56.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.vector_1),
                            contentDescription = stringResource(R.string.ShowPriority),
                            modifier = Modifier.size(120.dp)
                        )
                        Text(
                            text = task.priority.name,
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    )
}
