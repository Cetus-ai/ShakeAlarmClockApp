package com.griffith.shakealarmclockapp.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeDetector(
    context: Context,
    private val onShake: () -> Unit
) : SensorEventListener {

    val shakeSensitivity = 15f
    val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

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
            onShake()
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