package com.griffith.shakealarmclockapp.ui.theme.setting

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val context = LocalContext.current                                                              //The reason like in HomeScreen.kt. This context will help the intent to know where is actually is

    Scaffold{ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Button(
                    onClick = {
                        val newContext = context as ComponentActivity                               //I am in a Composable here, intents only work with Components. So i had to convert it into it so that the intent function '.finish()' can be used
                        newContext.finish()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .width(85.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 0.dp
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
                    text = "Settings",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            LazyColumn (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ){
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    TitelInputbox(
                        titel = "Snooze Time",
                        value = avm.SnoozeDurationProp.toString(),
                        onValueChange = { avm.SnoozeDurationProp = it.toIntOrNull()?: 0 }           //*1 The Input is directly transfer to the ViewModel. It's gonna be handle as a global setting. Not Alarm wise but Application wise
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    TitelSlidebar(
                        titel = "Alarm Volume",
                        value = avm.AlarmVolumeProp,
                        onValueChange = {range -> avm.AlarmVolumeProp = range}                      //*1
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    TextButton(onClick = {
                        val githubIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/Cetus-ai/ShakeAlarmClockApp")   //This time it's not a explicit intent but a implicit intent. This time im not saying exactly You are in Point A and go to Point B. Im just saying "go to the website, find your way"
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
