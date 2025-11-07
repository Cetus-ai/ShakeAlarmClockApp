package com.griffith.shakealarmclockapp.ui.theme.setting

import android.content.Intent
import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.ui.theme.setting.SettingsRepository
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(avm: AlarmViewModel){
    val context = LocalContext.current
//    var snoozeTime by remember { mutableStateOf("10") }
//    var alarmVolume by remember { mutableStateOf(50f) }

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

                })
                { Text("Cancel", color = Color.Blue, fontSize = 18.sp)}
                Text(
                    text = "Settings",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = {

                }) {
                    Text("Save", color = Color.Blue, fontSize = 18.sp)
                }
            }

            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
            ){
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
//                    SettingsItem(
//                        title = "Default Snooze Time",
//                        subtitle = "Input Box for a int/min",
//                    )
                    TitelInputbox(
                        titel = "Snooze Time",
                        value = avm.SnoozeDuration.toString(),
                        onValueChange = { avm.SnoozeDuration = it.toIntOrNull()?: 0 }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    TitelSlidebar(
                        titel = "Alarm Volume",
                        value = avm.AlarmVolume,
                        onValueChange = {range -> avm.AlarmVolume = range}
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    TextButton(onClick = {
                        val githubIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/Cetus-ai/ShakeAlarmClockApp")
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