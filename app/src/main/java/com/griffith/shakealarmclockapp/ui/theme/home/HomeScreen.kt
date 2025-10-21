package com.griffith.shakealarmclockapp.ui.theme.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(){
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
            FloatingActionButton(onClick = { addAlarmClick }) {
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
        }
    }
}