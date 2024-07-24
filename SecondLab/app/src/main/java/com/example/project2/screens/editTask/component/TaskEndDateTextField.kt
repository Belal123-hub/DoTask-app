package com.example.project2.screens.editTask.component

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.project2.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun Date.toFormattedString(): String {
    val simpleDateFormat = SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun String.toLongDate(): Long {
    val format = SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault())
    return format.parse(this)?.time ?: 0
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun TaskEndDateTextField(
    endDate: Long,
    onEndDateChanged: (Long) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    var selectedDate by rememberSaveable { mutableStateOf("") }
    var formattedEndDate by remember { mutableStateOf(Date(endDate).toFormattedString()) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = endDate

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val newDate = Calendar.getInstance()
            newDate.set(year, month, dayOfMonth)
            val newEndDate = newDate.timeInMillis
            onEndDateChanged.invoke(newEndDate)
            selectedDate = Date(newEndDate).toFormattedString()
            formattedEndDate = selectedDate
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )


    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 63.dp),
        readOnly = true,
        value = formattedEndDate,
        onValueChange = { newDate ->
            val newEndDate = newDate.toLongDate()
            onEndDateChanged.invoke(newEndDate)
            formattedEndDate = newDate
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            unfocusedTextColor = Color(0xff888888),
            focusedContainerColor = Color.Transparent,
            focusedTextColor = Color(0xff222222),
        ),
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Default.DateRange,
//                    contentDescription = stringResource(R.string.ArrangeDate_icon)
//                )
//            },
        interactionSource = interactionSource,
        placeholder = { Text(text = stringResource(R.string.deadline)) }
    )

    if (isPressed) {
        datePickerDialog.show()
    }
}
