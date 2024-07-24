package com.example.project2.screens.editTask

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project2.R
import com.example.project2.dataholder.InMemoryTasks
import com.example.project2.model.Task
import com.example.project2.model.TaskPriority
import com.example.project2.screens.editTask.component.TaskEndDateTextField
import com.example.project2.screens.editTask.component.TaskPriorityAutoComplete
import com.example.project2.screens.editTask.component.toFormattedString
import com.example.project2.ui.theme.black1
import com.example.project2.ui.theme.nunitoFontFamily
import java.nio.file.WatchEvent
import java.util.Date
import kotlin.random.Random

const val MAX_CHARACTER_LIMIT = 50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    onBackClicked: () -> Unit,
    onSaveClicked: () -> Unit,
) {
    val task = InMemoryTasks.selectedTaskOrNull
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var priority by remember { mutableStateOf(task?.priority?.name.orEmpty()) }
        var taskEndDate: Long = remember { task?.endDate?: 0 }
        var titleText by remember { mutableStateOf(task?.title.orEmpty()) }
        var descriptionText by remember { mutableStateOf(task?.description.orEmpty()) }
        var titleError by remember { mutableStateOf(false) }
        var descriptionError by remember { mutableStateOf(false) }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClicked,
                modifier=Modifier
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start =4.dp,
                        end = 4.dp
                    )
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back1),
                    modifier=Modifier
                        .padding(
                            top = 10.dp,
                            bottom = 10.dp,
                            start =4.dp,
                            end = 4.dp
                        )
                )
            }
            Text(
                text = stringResource(R.string.AddTasks), modifier=Modifier
                    .padding(
                        top = 18.dp,
                        bottom = 18.dp,
                        start =4.dp,
                        end = 4.dp
                    ),
                fontSize = 22.sp,
                fontFamily = nunitoFontFamily,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier =Modifier.height(16.dp))

        TextField(
            value = titleText,
            onValueChange = { newText -> titleText = newText },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = Color(0xff888888),
                focusedContainerColor = Color.Transparent,
                focusedTextColor = Color(0xff222222),
            ),
            label = { Text(text = stringResource(R.string.title),) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
                .padding(bottom = 1.dp),
        )

        if (titleError) {
            Text(
                text = stringResource(R.string.title_error),
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = descriptionText,
                onValueChange = { newText ->
                    if (newText.length <= MAX_CHARACTER_LIMIT) {
                        descriptionText = newText
                    }
                },
                textStyle = TextStyle.Default.copy(fontSize = 16.sp, lineHeight = 24.sp),
                label = { Text(text = stringResource(R.string.Description)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 328.dp, height = 127.dp),
                isError = descriptionError
            )

            if (descriptionError) {
                Text(
                    text = stringResource(R.string.description_error),
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                )
            }

            Text(
                text = " ${descriptionText.length}/$MAX_CHARACTER_LIMIT",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        Box(modifier =Modifier.fillMaxWidth() ){

            TaskEndDateTextField(
                endDate = taskEndDate,
                onEndDateChanged = { newEndDate ->
                    taskEndDate = newEndDate
                }
            )
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                tint = Color.Blue,
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .padding(end = 20.dp)
                    .size(30.dp)
                    .clickable {}
            )
        }

        TaskPriorityAutoComplete(
            modifier = Modifier.fillMaxWidth(),
            priority = priority,
            onPrioritySelected = { priority = it }
        )

        Spacer(modifier = Modifier.height(83.dp))

        Button(
            onClick = {
                titleError = titleText.isEmpty()
                descriptionError = descriptionText.isEmpty()

                if (titleError || descriptionError) {
                    return@Button
                }
                if (task!= null) {
                    InMemoryTasks.tasks.remove(task)
                    val updatedTask = task.copy(
                        title = titleText,
                        description = descriptionText,
                        priority = TaskPriority.fromString(priority.uppercase()),
                        endDate = taskEndDate,
                        formattedEndDate = Date(taskEndDate).toFormattedString(),
                    )
                    InMemoryTasks.tasks.add(updatedTask)
                }
                else {
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
                Toast.makeText(context, context.getString(R.string.task_saved), Toast.LENGTH_SHORT).show()
                onSaveClicked.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 17.dp, end = 17.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
        ) {
            Text(stringResource(R.string.Save))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditTaskScreenPreview() {
    EditTaskScreen(
        onBackClicked = {},
        onSaveClicked = {}
    )
}