package com.griffith.shakealarmclockapp.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.griffith.shakealarmclockapp.ui.theme.setting.SettingScreen
import com.griffith.shakealarmclockapp.viewmodel.AlarmAndroidViewModel

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val avm = remember { AlarmAndroidViewModel() }
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF2C2C2E)           //Background is define in main and works via NavGraph, this is a intent so it need it skipt the navgraph and need his own background color now
                )
            ){
                SettingScreen(avm)                                  //My Architecture is based on Composables. But Intents works with Components. So i had to implement a class who will help me and the intents to navigate
            }
        }
    }
}