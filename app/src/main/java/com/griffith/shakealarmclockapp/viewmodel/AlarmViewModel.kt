package com.griffith.shakealarmclockapp.viewmodel

import android.provider.ContactsContract
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.griffith.shakealarmclockapp.data.Alarm
import com.griffith.shakealarmclockapp.data.Note

class AlarmViewModel : ViewModel(){
    val alarms = mutableStateListOf<Alarm>()
    val notes = mutableStateListOf<Note>()

    private var snoozerDuration: Int = 5
    private var alarmVolume: Float = 50.0F
    var SnoozeDuration: Int
        get() {
            return snoozerDuration
        }
        set(value) {
            print(value)
            snoozerDuration = value
        }

    var AlarmVolume: Float
        get() {
            return alarmVolume
        }
        set(value) {
            print(value)
            alarmVolume = value
        }

    fun addAlarm(_name: String, _hour: Int, _minute: Int, _isEnable: Boolean, _days: List<String> ){    //Creating and adding Alarm to the List alarms after the user created it in CreateAlarmScreen.kt
        val newAlarm = Alarm(
            name = _name,
            hour = _hour,
            minute = _minute,
            isEnable = _isEnable,
            days = _days
        )
        alarms.add(newAlarm)
    }

    fun toggleAlarm(alarmId: String){                                                                   //The ViewModel dont have any direct access to the objects once they are "sended" to other activitys. Like a headquarter in a delivery company.
        val index = alarms.indexOfFirst { it.id == alarmId }                                            //That means if i want to change a alarm object here, i have to create a new one which is based on the old object but with my modifications.
        if (index != -1) {                                                                              //So I created a new one by coping the old alarm. Just this time with another togglestatus
            val alarm = alarms[index]
            val newState = !alarm.isEnable
            val updatedAlarm = alarm.copy(isEnable = newState)

            alarms[index] = updatedAlarm
        }
    }

    fun addNote(_alarmId: String, _text: String, _hour: Int, _minute: Int){                             //Creating and adding Note to the List notes after the user created it in CommentScreen.kt
        val note = Note(
            _alarmId,
            text = _text,
            _hour,
            _minute
        )
        notes.add(note)
    }

    fun filterNotes(_alarmId: String): List<Note>{                                                      //iterating through all the created notes and checking the id. With .filter, i return a List<Note> of all the notes which matched the alarmId
        return notes.filter { note -> note.alarmId == _alarmId }
    }
}