package com.griffith.shakealarmclockapp.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.PowerManager
import androidx.compose.ui.platform.LocalContext
import com.griffith.shakealarmclockapp.R

class AlarmReceiver : BroadcastReceiver(){

//    val context = LocalContext.current


    override fun onReceive(context: Context?, intent: Intent?) {
        var mediaPlayer = MediaPlayer.create(context, R.raw.air_raid_siren)
        val alarmId = intent?.getStringExtra("ALARM_ID")

    }

}