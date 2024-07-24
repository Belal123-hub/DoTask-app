package com.example.project2.screens.taskDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project2.R
import com.example.project2.dataholder.InMemoryTasks
import com.example.project2.model.Task
import com.example.project2.model.TaskPriority
import com.example.project2.screens.editTask.component.toFormattedString
import com.example.project2.ui.theme.nunitoFontFamily
import java.util.Date

@Composable
fun TaskDetailsScreen(
    onEditTaskClicked: (Task) -> Unit,
    onBackClicked: () -> Unit
) {
    val task = InMemoryTasks.selectedTask
    val formattedEndDate = Date(task.endDate).toFormattedString()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClicked) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF49454F),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
            Text(
                text = stringResource(R.string.Task),
                color = Color(0xFF1D1B20),
                modifier = Modifier.padding(start = 0.dp),
                fontFamily = nunitoFontFamily
                )
        }

        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = nunitoFontFamily
                    )

                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier,
                    onClick = { onEditTaskClicked.invoke(task) }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.edit_outlined),
                        contentDescription = null,
                        tint = Color(0xFF1554F6)
                    )
                }
            }
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp),
                text = stringResource(R.string.completed),
                color = if (task.isCompleted) {
                    Color.Green
                } else Color.Red,
                fontFamily = nunitoFontFamily
                )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = task.description,
                fontFamily = nunitoFontFamily
                )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.query_builder_24px_outlined),
                    contentDescription = stringResource(R.string.TimeIcon)
                )
                Text(
                    text = stringResource(R.string.before, formattedEndDate),
                    modifier = Modifier.padding(start = 20.dp),
                    fontFamily = nunitoFontFamily
                    )

                Spacer(modifier = Modifier.weight(1f))

                val backgroundColor = when (task.priority) {
                    TaskPriority.HIGH -> Color(0xFFFF685C)
                    TaskPriority.MEDIUM -> Color(0xFFFFD130)
                    TaskPriority.LOW -> Color(0xFF6579EA)
                    TaskPriority.NORMAL -> Color(0xFF52CC57)
                }

                Surface(
                    color = backgroundColor,
                    modifier = Modifier
                        .width(68.dp)
                        .height(24.dp),
                    shape = CutCornerShape(topStart = 12.dp, bottomStart = 12.dp),

                    ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = task.priority.name,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = nunitoFontFamily,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 4.dp)
                    )
                }
                }
            }
        }
    }

