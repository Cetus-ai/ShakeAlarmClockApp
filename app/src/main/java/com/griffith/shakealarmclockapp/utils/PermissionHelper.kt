package com.griffith.shakealarmclockapp.utils

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PermissionHelper(private val activity: ComponentActivity) {

    private val notificationLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {}

    fun frageNachBerechtigungen() {

        if (Build.VERSION.SDK_INT >= 33) {
            val hatBerechtigung = ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hatBerechtigung) {
                notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        if (Build.VERSION.SDK_INT >= 31) {
            val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                intent.data = Uri.parse("package:${activity.packageName}")
                activity.startActivity(intent)
            }
        }
    }
}