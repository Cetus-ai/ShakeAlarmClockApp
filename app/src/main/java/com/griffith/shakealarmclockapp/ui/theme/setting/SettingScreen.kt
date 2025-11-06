package com.griffith.shakealarmclockapp.ui.theme.setting

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun SettingScreen(){
    val context = LocalContext.current

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
//                    Intent(Intent.ACTION_MAIN).also {
//                        it.`package` =
//                    }
//                    val intent = Intent(context, )
                })
                { Text("Cancel", color = Color.Blue, fontSize = 18.sp)}
                Text(
                    text = "Settings",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = {}) {
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
                    SettingsItem(
                        title = "Default Snooze Time",
                        subtitle = "Input Box for a int/min"/*OutlinedTextField(
                        value = /*Default snoozer (5min)*/,
                        onValueChange = /*Safe the new Input as snoozer duration*/,
                        label = {Text(/*actual snoozer time*/)},
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .padding(top = 50.dp)
                    )*/,
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    SettingsItem(
                        title = "Alarm Volume",
                        subtitle = "a scalebar",
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item{
                    SettingsItem(
                        title = "Send Feedback",
                        subtitle = "Help me to improve",
                    )
                }
            }
        }
    }
}