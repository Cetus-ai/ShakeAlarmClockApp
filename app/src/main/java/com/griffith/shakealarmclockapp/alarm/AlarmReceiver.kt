package com.griffith.shakealarmclockapp.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.griffith.shakealarmclockapp.wakeup.WakeUpManager
import java.util.Calendar

class AlarmReceiver : BroadcastReceiver(){

//    val context = LocalContext.current

    // Handles alarm: start AlarmSerivce (Noticiation and Sound) and schedule next alarm
    override fun onReceive(context: Context, intent: Intent) {
        android.util.Log.d("AlarmReceiver", "Alarm received!")
        val alarmId = intent.getStringExtra("ALARM_ID") ?: return
        val hour = intent.getIntExtra("HOUR", 0)
        val minute = intent.getIntExtra("MINUTE", 0)
        val selectedDays = intent.getIntArrayExtra("SELECTED_DAYS")

        val alarmIntent = Intent(context, AlarmService::class.java)
        alarmIntent.putExtra("ALARM_ID", alarmId)
        ContextCompat.startForegroundService(context, alarmIntent)

        if (selectedDays != null) {
            if (selectedDays.isNotEmpty()) {
                val scheduler = AlarmScheduler(context)
                val daysList  = selectedDays.map { calendarDayToString(it) }

                scheduler.scheduleAlarm(alarmId, hour, minute, daysList)
            }
        }

        WakeUpManager.startWakeUp(context, alarmId)
    }

    // Converts Calendar constants back to string format for scheduleAlarm()
    //    That seems unnecessarily complicated to me
    //    It must have been the work of an old and very tired Roman
    private fun calendarDayToString(day: Int): String {
        return when(day) {
            Calendar.MONDAY -> "Mo"
            Calendar.TUESDAY -> "Tu"
            Calendar.WEDNESDAY -> "We"
            Calendar.THURSDAY -> "Th"
            Calendar.FRIDAY -> "Fr"
            Calendar.SATURDAY -> "Sa"
            Calendar.SUNDAY -> "Su"
            else -> ""
        }
    }
}