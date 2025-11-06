package com.griffith.shakealarmclockapp.ui.theme.home

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.data.Alarm

@Composable
fun HomeScreen(
    alarmsListing: List<Alarm>,
    onAddAlarmClick: () -> Unit,
    onToggleAlarm: (Alarm) -> Unit,
    editAlarm: (Alarm) -> Unit
){
    val context = LocalContext.current

    Scaffold(

        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
                FloatingActionButton(                                                           //The '+' Button
                    onClick = onAddAlarmClick,
                    modifier = Modifier.align( Alignment.BottomEnd )
                ) {
                    Icon(Icons.Filled.Add, "Add Alarm")
                }

                FloatingActionButton(                                                          //The Setting Button
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.BottomStart )
                        .padding(start = 30.dp)
                ) {
                    Icon(Icons.Default.Settings, "Settings")
                }
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
                text = "Alarms",                                                                //The Titel on the Top
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            LazyColumn (                                                                        //A LazyColumn in which I iterate through a list of alarms and list them here
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
                }
            }
        }
    }
}