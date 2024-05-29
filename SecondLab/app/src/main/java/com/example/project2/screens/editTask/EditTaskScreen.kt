package com.example.project2.screens.editTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.project2.R
import com.example.project2.dataholder.InMemoryTasks
import com.example.project2.model.Task
import com.example.project2.model.TaskPriority
import com.example.project2.screens.editTask.component.TaskEndDateTextField
import com.example.project2.screens.editTask.component.TaskPriorityAutoComplete
import com.example.project2.screens.editTask.component.toFormattedString
import com.example.project2.ui.theme.black1
import java.util.Date
import kotlin.random.Random

const val MAX_CHARACTER_LIMIT = 50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    task: Task? = null,
    onBackClicked: () -> Unit,
    onSaveClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.AddTasks),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back1))
                    }
                },

                modifier = Modifier.fillMaxWidth()
            )
        },
        content = {
            var priority by remember { mutableStateOf(task?.priority?.name.orEmpty()) }
            var taskEndDate: Long = remember { task?.endDate ?: 0 }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                var titleText by remember { mutableStateOf(task?.title.orEmpty()) }
                TextField(
                    value = titleText,
                    onValueChange = { newText -> titleText = newText },
                    label = { Text(text = stringResource(R.string.title)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )

                Spacer(modifier = Modifier.height(3.dp))

                var descriptionText by remember { mutableStateOf(task?.description.orEmpty()) }

                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = { newText ->
                        if (newText.length <= MAX_CHARACTER_LIMIT) {
                            descriptionText = newText
                        }
                    },
                    label = { Text(text = stringResource(R.string.Description)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                )

                Text(text = " ${descriptionText.length}/$MAX_CHARACTER_LIMIT")

                Spacer(modifier = Modifier.height(3.dp))

                TaskEndDateTextField(
                    endDate = taskEndDate,
                    onEndDateChanged = { newEndDate ->
                        taskEndDate = newEndDate
                    }
                )

                Spacer(modifier = Modifier.height(3.dp))

                TaskPriorityAutoComplete(
                    modifier = Modifier.fillMaxWidth(),
                    priority = priority,
                    onPrioritySelected = { priority = it }
                )

                Spacer(modifier = Modifier.height(3.dp))

                Button(
                    onClick = {
                        if (task != null) {
                            InMemoryTasks.tasks.remove(task)
                            val updatedTask = task.copy(
                                title = titleText,
                                description = descriptionText,
                                priority = TaskPriority.fromString(priority.uppercase()),
                                endDate = taskEndDate,
                                formattedEndDate = Date(taskEndDate).toFormattedString(),
                            )
                            InMemoryTasks.tasks.add(updatedTask)
                        } else {
                            InMemoryTasks.tasks.add(
                                Task(
                                    id = Random.nextInt(),
                                    title = titleText,
                                    description = descriptionText,
                                    isCompleted = false,
                                    priority = TaskPriority.fromString(priority.uppercase()),
                                    endDate = taskEndDate,
                                    formattedEndDate = Date(taskEndDate).toFormattedString()
                                )
                            )
                        }
                        onSaveClicked.invoke()
                    },
                    colors = ButtonDefaults.buttonColors(black1),
                    shape = RoundedCornerShape(5),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(stringResource(R.string.Save))
                }
            }
        }
    )
}
