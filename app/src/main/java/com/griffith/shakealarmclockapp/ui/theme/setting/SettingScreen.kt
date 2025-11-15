package com.griffith.shakealarmclockapp.ui.theme.setting

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    avm: AlarmViewModel,
){
    val context = LocalContext.current                                                          //The reason like in HomeScreen.kt. This context will help the intent to know where is actually is

    Scaffold{ paddingValues ->
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
                TextButton(onClick = {
                    val newContext = context as ComponentActivity                               //I am in a Composable here, intents only work with Components. So i had to convert it into it so that the intent function '.finish()' can be used
                    newContext.finish()
                })
                { Text("Cancel", color = Color.Blue, fontSize = 18.sp)}
                Text(
                    text = "Settings",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
            ){
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    TitelInputbox(
                        titel = "Snooze Time",
                        value = avm.SnoozeDurationProp.toString(),
                        onValueChange = { avm.SnoozeDurationProp = it.toIntOrNull()?: 0 }       //*1 The Input is directly transfer to the ViewModel. It's gonna be handle as a global setting. Not Alarm wise but Application wise
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    TitelSlidebar(
                        titel = "Alarm Volume",
                        value = avm.AlarmVolumeProp,
                        onValueChange = {range -> avm.AlarmVolumeProp = range}                  //*1
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    TextButton(onClick = {
                        val githubIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/Cetus-ai/ShakeAlarmClockApp")     //This time it's not a explicit intent but a implicit intent. This time im not saying exactly You are in Point A and go to Point B. Im just saying "go to the website, find your way"
                        )
                        context.startActivity(githubIntent)
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TitelSubtitle(
                            title = "Visit GitHub",
                            subtitle = "Help me improve this app",
                        )
                    }
                }
            }
        }
    }
}