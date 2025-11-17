package com.griffith.shakealarmclockapp.wakeup

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel


//- needed to Navigate to WakeUpScreen
class WakeUpActivity : ComponentActivity() {
//    - Displays UI (screen)
//    - Reminder list
//    - Shake animation
//    - Dismiss/Snooze buttons

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        setContent {

            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF2C2C2E)
                )
            ) {
                WakeUpScreen(

                )
            }
        }
    }
}