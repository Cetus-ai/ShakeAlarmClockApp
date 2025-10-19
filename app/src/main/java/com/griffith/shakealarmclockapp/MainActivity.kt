package com.griffith.shakealarmclockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF2C2C2E)
                )
            ){
//                val navController = rememberNavController()
                var alarmName by remember { mutableStateOf("") }
                var note by remember { mutableStateOf(" ")}

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ){
                    composable("home"){
//                        Scaffold(
//                            floatingActionButton = {
//                                FloatingActionButton(onClick = {navController.navigate("create")}) {
//                                    Icon(Icons.Filled.Add, "Add Alarm")
//                                }
//                            }
//                        ){ paddingValues ->
//                            Column (
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(paddingValues),
//                                horizontalAlignment = Alignment.CenterHorizontally
//                            ){
//                                Text(
//                                    text = "Alarms",
//                                    fontSize = 30.sp,
//                                    color = Color.White,
//                                    fontWeight = FontWeight.Bold
//                                )
//                            }
//                        }
                    }

                    composable("create"){
                        Scaffold()
                        { paddingValues ->

                            val timerPickerState = rememberTimePickerState(
                                initialHour = 6,
                                initialMinute = 30,
                                is24Hour = false
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Row (
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    TextButton(onClick = {
                                        navController.popBackStack()
                                    }) { Text("Cancel", color = Color.Blue, fontSize = 18.sp)}
                                    Text(
                                        text = "Set Alarm",
                                        fontSize = 30.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                    TextButton(onClick = {
                                        navController.popBackStack()
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
                                        modifier = Modifier.padding(bottom = 32.dp)
                                    )

                                    TimePicker(state = timerPickerState)

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Box(modifier = Modifier.scale(0.8f)){
                                            //TimePicker(state = timerPickerState)
                                            TimeInput(
                                                modifier = Modifier,
                                                state = timerPickerState
                                            )
                                        }
                                        OutlinedTextField(
                                            value = note,
                                            onValueChange = {note = it},
                                            label = {Text("Note")},
                                            modifier = Modifier.padding(start = 10.dp, bottom = 80.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}