package com.griffith.shakealarmclockapp.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.griffith.shakealarmclockapp.ui.theme.setting.SettingScreen

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF2C2C2E)          //Background is define in main and works via NavGraph, this is a intent so it need it skipt the navgraph and need his own background color now
                )
            ){
                SettingScreen()
            }
        }
    }
}