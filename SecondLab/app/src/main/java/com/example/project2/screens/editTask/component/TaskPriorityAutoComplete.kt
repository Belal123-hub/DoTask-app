package com.example.project2.screens.editTask.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.project2.R
import com.example.project2.model.TaskPriority

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskPriorityAutoComplete(
    modifier: Modifier,
    priority: String = "",
    onPrioritySelected: (String) -> Unit = {}
) {

    val priorities = TaskPriority.entries.map { it.name }

    val heightTextFields by remember {
        mutableStateOf(55.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp,top = 28.dp, bottom = 83.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(heightTextFields)
                .shadow(elevation = 6.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFf0ecec),
                unfocusedTextColor = Color(0xff888888),
                focusedContainerColor = Color(0xFFf0ecec),
                focusedTextColor = Color(0xff222222),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = priority,
            onValueChange = {
                onPrioritySelected(it)
                expanded = true
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { expanded =!expanded }) {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            },
            placeholder = { Text(stringResource(R.string.priority)) }
        )

        AnimatedVisibility(visible = expanded) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .width(textFieldSize.width.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 150.dp),
                ) {
                    if (priority.isNotEmpty()) {
                        items(
                            priorities.filter {
                                it.lowercase()
                                    .contains(priority.lowercase()) || it.lowercase()
                                    .contains("others")
                            }
                                .sorted()
                        ) {
                            CategoryItems(title = it) { title ->
                                onPrioritySelected(title)
                                expanded = false
                            }
                        }
                    }
                    else {
                        items(
                            priorities.sorted()
                        ) {
                            CategoryItems(title = it) { title ->
                                onPrioritySelected(title)
                                expanded = false
                            }
                        }
                    }

                }
            }
        }
    }
}
