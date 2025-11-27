package com.griffith.shakealarmclockapp.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.griffith.shakealarmclockapp.wakeup.WakeUpActivity
import java.lang.Math.sqrt
import java.lang.StrictMath.sqrt
import kotlin.math.sqrt

class ShakeDetector(
    private val onShake: () -> Unit
) : SensorEventListener {

    val shakeSensitivity = 15f

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event?.values[0]
        val y = event?.values[1]
        val z = event?.values[2]

        val acceleration = sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH

        if(acceleration > shakeSensitivity){
            onShake()
        }
    }
}