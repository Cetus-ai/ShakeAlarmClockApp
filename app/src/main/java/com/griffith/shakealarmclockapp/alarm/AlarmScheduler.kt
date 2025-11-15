package com.griffith.shakealarmclockapp.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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

    fun scheduleAlarm(alarmId: String, hour: Int, minute: Int){
        if(!checkPermission())
            return

        val receiverContact = Intent(context, AlarmReceiver::class.java)
            .putExtra("ALARM_ID", alarmId)
            .putExtra("HOUR", hour)
            .putExtra("MINUTE", minute)

        val executeWhenAlarm = PendingIntent.getBroadcast(
            context,
            alarmId.hashCode(),
            receiverContact,
            PendingIntent.FLAG_MUTABLE
        )

        alarmManager.setAlarmClock(executeWhenAlarm)
    }
}