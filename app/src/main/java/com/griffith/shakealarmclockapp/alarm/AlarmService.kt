package com.griffith.shakealarmclockapp.alarm

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.security.Provider
import com.griffith.shakealarmclockapp.R


class AlarmService : Service(){

    var mediaPlayer = MediaPlayer.create(this, R.raw.air_raid_siren)

    override fun onBind(intent: Intent): IBinder {

    }

    override fun onStartCommand(intent: Intent) {
        val alarmId = intent.getStringExtra("ALARM_ID")
        startForeground(1, buildNotification(alarmId))

        mediaPlayer.start()
    }

    fun stopPlayer(){
        mediaPlayer.stop()
        mediaPlayer.release()
    }


    override fun onStop() {

    }

    fun buildNotification(alarmId: String?): Notification {
        return NotificationCompat.Builder(this, "alarm")
            .setContentTitle("Alarm")
            .setContentText("Alarm is working")
            .setSmallIcon(R.drawable.alarm_icon)
            .build()
    }
}