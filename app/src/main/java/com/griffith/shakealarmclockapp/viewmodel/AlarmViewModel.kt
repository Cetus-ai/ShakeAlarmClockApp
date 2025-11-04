package com.griffith.shakealarmclockapp.viewmodel

import android.provider.ContactsContract
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.griffith.shakealarmclockapp.data.Alarm
import com.griffith.shakealarmclockapp.data.Note

class AlarmViewModel : ViewModel(){
    val alarms = mutableListOf<Alarm>()
    val notes = mutableStateListOf<Note>()

    fun addAlarm(_name: String, _hour: Int, _minute: Int, _isEnable: Boolean, _days: List<String> ){
        val newAlarm = Alarm(
            name = _name,
            hour = _hour,
            minute = _minute,
            isEnable = _isEnable,
            days = _days
        )
        alarms.add(newAlarm)
    }

    fun toggleAlarm(alarmId: String){
        val index = alarms.indexOfFirst { it.id == alarmId }
        if (index != -1) {
            val alarm = alarms[index]
            if(alarm.isEnable){
                false
            }else{
                true
            }
        }
    }

    fun addNote(_text: String, _hour: Int, _minute: Int){
        val note = Note(
            text = _text,
            _hour,
            _minute
        )
        notes.add(note)
    }
}