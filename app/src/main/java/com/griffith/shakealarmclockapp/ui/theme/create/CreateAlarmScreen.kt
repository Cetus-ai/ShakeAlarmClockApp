package com.griffith.shakealarmclockapp.ui.theme.create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
    val weekdays = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")                               //weekdays and days are working parallel to each other (Index[0] weekdays refers to Index[0] in days).
    val days = remember { mutableStateListOf<Boolean>().apply {                                   //One is saving the days of the Week to display them(weekdays), the other list will safe which day the user choose
        repeat(7) { add(false) }                                                          //days[0] == true means that the user selected that the alarm shoud ring on monday
    }}

    val timerPickerState = rememberTimePickerState(                                               //Define a TimePicker with a default time and respecting the am/pm Version
        initialHour = 6,                                                                          //The User can edit the time, the default time is just that i can shows something to begin
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(                                                                   //I can easily swapping the booleans and colors of each day on and off
                    onClick = onCancel,
                    modifier = Modifier
                        .width(85.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF9D4EDD),
                        contentColor = Color.White
                    ),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Set Alarm",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {                                                                     //Pressing the Safe Button will Create a Alarm
                        val result = mutableListOf<String>()                                        //for that i iterate through val days. Based on the booleans in this list i can collect the days where the alarm shoud ring(safed in result)
                        for (i in days.indices) {
                            if (days[i]) {
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
                    },
                    modifier = Modifier
                        .width(85.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF9D4EDD),
                        contentColor = Color.White
                    ),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Save",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(                                                                //The User can give a Name to his Alarm (Optional)
                    value = alarmName,
                    onValueChange = { alarmName = it },
                    label = { Text("Alarm Name") },
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .padding(top = 50.dp)
                )

                TimePicker(state = timerPickerState)                                              //Presenting the TimePicker we created in line 45

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    weekdays.forEachIndexed { index, day ->                                       //Iterating through weekdays. As i said they are working parallel to each other
                        Button(                                                                   //I can easily swapping the booleans and colors of each day on and off
                            onClick = { days[index] = !days[index] },
                            modifier = Modifier.size(45.dp),
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(days[index]) Color(0xFFFFA500) else Color(0xFF9D4EDD),
                                contentColor = Color.White
                            ),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                        ) {
                            Text(
                                text = day.substring(0, 2),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}