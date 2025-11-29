package com.griffith.shakealarmclockapp.wakeup

import android.content.Context
import android.content.Intent
import com.griffith.shakealarmclockapp.alarm.AlarmService
import com.griffith.shakealarmclockapp.data.Note
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel
import kotlin.time.Duration

class WakeUpManager{
    companion object{
        var noteList: List<Note> = emptyList()

        //Start the WakeUpActivity, delete duplicates from the stack and start in a new Task
        fun startWakeUp(context: Context, alarmId: String){
            val intent = Intent(context, WakeUpActivity::class.java).apply {
                putExtra("ALARM_ID", alarmId)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            context.startActivity(intent)
        }

        //Get the notes from the ViewModel
        fun loadReminders(alarmId: String, viewModel: AlarmViewModel): List<Note>{
            noteList = viewModel.notes.filter { true }
            return noteList
        }

//        fun shakeDetected(): Boolean{
//
//        }

        // Stops the alarm sound service and resets the WakeUpManager state (at the moment just clearing the notes)
        fun dismissAlarm(context: Context){
            val serviceIntent = Intent(context, AlarmService::class.java)
            context.stopService(serviceIntent)

            reset()
        }

        //Clearing notes, another alarms ring, other notes are required
        fun reset(){
            noteList = emptyList()
        }

        //The snoozefunction (im working on)
        fun snoozeAlarm(context: Context, snoozeDuration: Int){
//            TODO
            dismissAlarm(context)
        }
    }

}