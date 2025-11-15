package com.griffith.shakealarmclockapp.alarm

import android.os.Build

class AlarmScheduler {
//    val context = androidx.compose.ui.platform.LocalContext.current

    fun checkPermission(){
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){

        }
    }
}