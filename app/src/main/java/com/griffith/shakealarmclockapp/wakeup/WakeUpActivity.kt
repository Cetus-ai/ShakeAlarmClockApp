package com.griffith.shakealarmclockapp.wakeup

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel

class WakeUpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )


        setContent {
            val viewModel: AlarmViewModel = viewModel(
                factory = AlarmViewModel.Factory
            )

            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF2C2C2E)
                )
            ) {
                WakeUpScreen(
                    alarmId = intent.getStringExtra("ALARM_ID"),
                    viewModel = viewModel,
                    onDismiss = {
                        WakeUpManager.dismissAlarm(context = this)
                        finish()
                    },
                    onSnooze = { duration ->
                        WakeUpManager.snoozeAlarm(this, duration)
                        finish()
                    }
                )
            }
        }
    }
}