package com.griffith.shakealarmclockapp.ui.theme.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.data.Alarm

@Composable
//@Preview
fun HomeScreen(
    alarmsListing: List<Alarm>,
    onAddAlarmClick: () -> Unit,
    onToggleAlarm: (Alarm) -> Unit,
    editAlarm: (Alarm) -> Unit
){
    Scaffold(
        floatingActionButton = {
            val addAlarmClick = null
            FloatingActionButton(onClick = onAddAlarmClick ) {
                Icon(Icons.Filled.Add, "Add Alarm")
            }
        }
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Alarms",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ){
                items(alarmsListing){ _alarm ->
                    AlarmItem(
                        alarm = _alarm,
                        onToggle = {onToggleAlarm(_alarm)},
                        edit = {editAlarm(_alarm)}
                    )
                    Spacer(modifier = Modifier.height(12.dp))
//                    Alarm(
//                        alarm.id,
//                        alarm.name,
//                        alarm.hour,
//                        alarm.minute,
//                        alarm.isEnable,
//                        days = alarm.days
//                    )
//                    Text("id: " + alarm.id + "\nname: " + alarm.name + "\n" + alarm.hour + ":" + alarm.minute + "\nOn/Off: " + alarm.isEnable + "\nDays: " + alarm.days)
                }
            }
        }
    }
}