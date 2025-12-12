package com.griffith.shakealarmclockapp.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.griffith.shakealarmclockapp.wakeup.WakeUpManager.Companion.reset
import kotlin.math.sqrt

class ShakeDetector(
    context: Context,
    val shakeSensitivity: Float = 10f,
    var requiredShakes: Int = 10,
    val onShakeProgress: (Int, Int) -> Unit,
    val onShake: () -> Unit
) : SensorEventListener {

//    val currentTime = System.currentTimeMillis()
    val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    var shakeCount = 0
    var lastshakeTime = 0L
    var shakeDetection = 300L

    //inherits from SensorEventListener, the fun onAccuracyChanged must occur, but I don't need it
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    //When the mobile phone is accelerated, it calculates the exact acceleration - GRAVITY_EARTH
    //if acceleration is heigher then sensitivity = shake
    override fun onSensorChanged(event: SensorEvent?) {

        if (event == null) return

        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        val acceleration = sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH

        if(acceleration > shakeSensitivity){
            val currentTime = System.currentTimeMillis()
            if(currentTime - lastshakeTime > shakeDetection){
                lastshakeTime = currentTime
                shakeCount++

                onShakeProgress(shakeCount, requiredShakes)

                if(shakeCount >= requiredShakes){
                    onShake()
                    reset()
                }
            }
        }
    }

    //Add Listener to the accelerometer
    fun start(){
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    //Stop the Sensor
    fun stop(){
        sensorManager.unregisterListener(this)
    }
}