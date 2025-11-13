package com.griffith.shakealarmclockapp.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class AlarmReceiver : BroadcastReceiver(){

//    val context = LocalContext.current


    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmId = intent?.getStringExtra("ALARM_ID")
        val contextNull = context ?: return

        val alarmIntent = Intent(contextNull, AlarmService::class.java)
        alarmIntent.putExtra("ALARM_ID", alarmId)
        ContextCompat.startForegroundService(context, alarmIntent)
    }

}