package com.griffith.shakealarmclockapp.wakeup

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.griffith.shakealarmclockapp.sensor.ShakeDetector
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel

class WakeUpActivity : ComponentActivity() {

    var shakeDetector: ShakeDetector? = null

    var shakeProgress by mutableStateOf(0)
    var shakeTarget by mutableStateOf(20)

    // Sets up alarm screen: unlock flags, shake detection, and UI
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        shakeDetector?.start()

        setContent {
            val viewModel: AlarmViewModel = viewModel(
                factory = AlarmViewModel.Factory
            )

            val sensitivity = viewModel.ShakeIntensityProp

            shakeDetector = ShakeDetector(
                context = this,
                shakeSensitivity = sensitivity,
                requiredShakes = 20,
                onShakeProgress = { current, total ->
                    shakeProgress = current
                    shakeTarget = total
                },
                onShake = {
                    WakeUpManager.dismissAlarm(context = this)
                    finish()
                }
            )
            shakeDetector?.start()

            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF2C2C2E)
                )
            ) {
                WakeUpScreen(
                    alarmId = intent.getStringExtra("ALARM_ID"),
                    viewModel = viewModel,
                    shakeProgress = shakeProgress,
                    shakeTarget = shakeTarget,
                    onDismiss = {
                        WakeUpManager.dismissAlarm(context = this)
                        finish()
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        shakeDetector?.stop()
        super.onDestroy()
    }
}