package com.griffith.shakealarmclockapp.ui.theme.CommentScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CommentForm(){
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    Column {
        TimeInput(
            state = timePickerState,
        )
    }
}