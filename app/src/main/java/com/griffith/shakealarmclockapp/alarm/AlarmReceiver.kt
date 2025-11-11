package com.griffith.shakealarmclockapp.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import com.griffith.shakealarmclockapp.R

class AlarmReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        var mediaPlayer = MediaPlayer.create(context, R.raw.air_raid_siren)


    }

}