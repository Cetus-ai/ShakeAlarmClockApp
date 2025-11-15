package com.griffith.shakealarmclockapp.alarm

import android.app.AlarmManager
import android.content.Context
import android.os.Build

class AlarmScheduler(private val context: Context) {
    val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun checkPermission(): Boolean{
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            alarmManager.canScheduleExactAlarms()
        }else{
            true
        }
    }
}