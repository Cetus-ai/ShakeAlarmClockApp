package com.griffith.shakealarmclockapp.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import kotlinx.coroutines.selects.select
import java.util.Calendar
import java.util.stream.Collectors.mapping
import kotlin.collections.toIntArray

class AlarmScheduler(private val context: Context) {
    val alarmManager = context.getSystemService(AlarmManager::class.java)
//    val dayList: ArrayList<Int> = null;

    fun checkPermission(): Boolean{
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            alarmManager.canScheduleExactAlarms()
        }else{
            true
        }
    }

    fun scheduleAlarm(alarmId: String, hour: Int, minute: Int, days: List<String>){

        val selectedDays = daysToCalender(days)

        if(!checkPermission())
            return

        val receiverContact = Intent(context, AlarmReceiver::class.java)
            .putExtra("ALARM_ID", alarmId)
            .putExtra("HOUR", hour)
            .putExtra("MINUTE", minute)
            .putExtra("SELECTED_DAYS", selectedDays)

        val executeWhenAlarm = PendingIntent.getBroadcast(
            context,
            alarmId.hashCode(),
            receiverContact,
            PendingIntent.FLAG_IMMUTABLE
        )

//        val exactAlarmTimer = Calendar.getInstance().apply {
//            set(Calendar.MILLISECOND, 0)
//            set(Calendar.SECOND, 0)
//            set(Calendar.MINUTE, minute)
//            set(Calendar.HOUR_OF_DAY, hour)
//        }


        val triggerAt = getNextAlarmTimer(
            hour,
            minute,
            selectedDays.toCollection(ArrayList())
        )

//        if(triggerAt == now){
//            exactAlarmTimer.add(Calendar.DAY_OF_YEAR, 1)
//        }

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

    fun daysToCalender(days: List<String>): IntArray{
        val daysMap = mapOf(
            "Mo" to Calendar.MONDAY,
            "Tu" to Calendar.TUESDAY,
            "We" to Calendar.WEDNESDAY,
            "Th" to Calendar.THURSDAY,
            "Fr" to Calendar.FRIDAY,
            "Sa" to Calendar.SATURDAY,
            "Su" to Calendar.SUNDAY,
        )

        return days.mapNotNull { daysMap[it] }.toIntArray()
    }

    fun getNextAlarmTimer(hour: Int, minute: Int, selectedDays: ArrayList<Int>): Long{
        val exactAlarmTimer = Calendar.getInstance().apply {
            set(Calendar.MILLISECOND, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MINUTE, minute)
            set(Calendar.HOUR_OF_DAY, hour)
        }

        val now = System.currentTimeMillis()

        for (i in 0..7) {
            val dayOfWeek = exactAlarmTimer.get(Calendar.DAY_OF_WEEK)

            if (selectedDays.contains(dayOfWeek) && exactAlarmTimer.timeInMillis > now) {
                return exactAlarmTimer.timeInMillis
            }

            exactAlarmTimer.add(Calendar.DAY_OF_YEAR, 1)
        }
        return exactAlarmTimer.timeInMillis             //<-- shoud never reach this, if so, something went wrong
    }
}