package com.griffith.shakealarmclockapp.ui.theme.create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAlarm(
    onCancel: () -> Unit,
    onSafeAlarmClick: (String, Int, Int, Boolean, List<String>) -> Unit
){

    var alarmName by remember { mutableStateOf("") }
    val weekdays = listOf<String>("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
    val days = remember { mutableStateListOf<Boolean>().apply {
        repeat(7) { add(false) }
    }}

    val timerPickerState = rememberTimePickerState(
        initialHour = 6,
        initialMinute = 30,
        is24Hour = false
    )

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                TextButton(onClick = onCancel)
                { Text("Cancel", color = Color.Blue, fontSize = 18.sp)}
                Text(
                    text = "Set Alarm",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = {
                    val result = mutableListOf<String>()
                    for(i in days.indices){
                        if(days[i]){
                            result.add(weekdays[i])
                        }
                    }
                    onSafeAlarmClick(
                        alarmName,
                        timerPickerState.hour,
                        timerPickerState.minute,
                        timerPickerState.is24hour,
                        result
                    )
                }) {
                    Text("Save", color = Color.Blue, fontSize = 18.sp)
                }
            }
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                OutlinedTextField(
                    value = alarmName,
                    onValueChange = { alarmName = it },
                    label = {Text("Alarm Name")},
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .padding(top = 50.dp)
                )

                TimePicker(state = timerPickerState)

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    weekdays.forEachIndexed { index, day ->
                    TextButton(onClick = { days[index] = !days[index] }) {
                        Text(day,
                            color = if(days[index]) Color(0xFFFFA500) else Color.LightGray,
                            fontSize = 18.sp
                        ) }
                    }
                }
            }
        }
    }
}
