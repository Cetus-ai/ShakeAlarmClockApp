package com.griffith.shakealarmclockapp.alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.griffith.shakealarmclockapp.R


class AlarmService : Service(){

    var mediaPlayer : MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val alarmId = intent.getStringExtra("ALARM_ID")

        ensureChannel()
        startForeground(1, buildNotification(alarmId))

        mediaPlayer = MediaPlayer.create(this, R.raw.air_raid_siren)

        mediaPlayer?.start()

        return START_NOT_STICKY
    }

//    fun stopPlayer(){
//        mediaPlayer?.stop()
//        mediaPlayer?.release()
//    }


    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    fun buildNotification(alarmId: String?): Notification {
        return NotificationCompat.Builder(this, "alarm")
            .setContentTitle("Alarm")
            .setContentText("Alarm is working")
            .setSmallIcon(R.drawable.alarm_icon)
            .build()
    }

    fun ensureChannel(){
        if(Build.VERSION.SDK_INT < 26)
            return

        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            "alarm",
            "Alarm",
            NotificationManager.IMPORTANCE_HIGH
        )
        manager.createNotificationChannel(channel)
    }
}