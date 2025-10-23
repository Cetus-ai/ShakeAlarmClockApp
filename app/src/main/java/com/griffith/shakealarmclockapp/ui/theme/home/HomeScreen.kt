package com.griffith.shakealarmclockapp.ui.theme.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.data.Alarm
import com.griffith.shakealarmclockapp.viewmodel.alarms

@Composable
fun HomeScreen(
    alarms: List<Alarm>,
    onAddAlarmClick: () -> Unit
){
//    alarms: List<Alarm>
    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                val navController = null
//                navController.navigate("create")
//            }) {
//                Icon(Icons.Filled.Add, "Add Alarm")
//            }
//        }

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
                    .padding(paddingValues)
            ){
                items(alarms){ alarm ->
                    Alarm(
                        alarm.id,
                        alarm.name,
                        alarm.hour,
                        alarm.minute,
                        alarm.isEnable
                    )
                }
            }
        }
    }
}