package com.griffith.shakealarmclockapp.wakeup

import android.content.Context
import android.content.Intent
import android.text.BoringLayout
import androidx.activity.ComponentActivity
import com.griffith.shakealarmclockapp.alarm.AlarmService
import com.griffith.shakealarmclockapp.data.Note
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel
import kotlin.time.Duration

class WakeUpManager{
    companion object{
        var noteList: List<Note> = emptyList()
        fun startWakeUp(context: Context, alarmId: String){
            val intent = Intent(context, WakeUpActivity::class.java).apply {
                putExtra("ALARM_ID", alarmId)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            context.startActivity(intent)
        }

        fun loadReminders(alarmId: String, viewModel: AlarmViewModel): List<Note>{
            noteList = viewModel.notes
            return noteList
        }

//        fun shakeDetected(): Boolean{
//
//        }

        fun dismissAlarm(context: Context){
            val serviceIntent = Intent(context, AlarmService::class.java)
            context.stopService(serviceIntent)

            reset()
        }

        fun reset(){
            noteList = emptyList()
        }

        fun snoozeAlarm(context: Context, snoozeDuration: Int){
//            TODO
            dismissAlarm(context)
        }
    }

}