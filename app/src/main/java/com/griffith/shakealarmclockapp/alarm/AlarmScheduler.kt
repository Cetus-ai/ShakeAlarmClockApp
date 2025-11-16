package com.griffith.shakealarmclockapp.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import java.util.Calendar

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
            PendingIntent.FLAG_IMMUTABLE
        )

        val exactAlarmTimer = Calendar.getInstance().apply {
            set(Calendar.MILLISECOND, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, minute)
            set(Calendar.HOUR_OF_DAY, hour)
        }

        val now = System.currentTimeMillis()

        val triggerAt = exactAlarmTimer.timeInMillis
        if(triggerAt == now){
            exactAlarmTimer.add(Calendar.DAY_OF_YEAR, 1)
        }

        val alarmInfo = AlarmManager.AlarmClockInfo(triggerAt, executeWhenAlarm)
        alarmManager.setAlarmClock(alarmInfo, executeWhenAlarm)
    }

    fun cancelAlarm(alarmId: String){
        val receiverContact = Intent(context, AlarmReceiver::class.java)
            .putExtra("ALARM_ID", alarmId)

        val executeWhenAlarm = PendingIntent.getBroadcast(
            context,
            alarmId.hashCode(),
            receiverContact,
            PendingIntent.FLAG_IMMUTABLE
        )

       alarmManager.cancel(executeWhenAlarm)
    }
}