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
            val newState = !alarm.isEnable
            val updatedAlarm = alarm.copy(isEnable = newState)

            alarms[index] = updatedAlarm
        }
    }

    fun addNote(_alarmId: String, _text: String, _hour: Int, _minute: Int){
        val note = Note(
            _alarmId,
            text = _text,
            _hour,
            _minute
        )
        notes.add(note)
    }

    fun filterNotes(_alarmId: String): List<Note>{
        return notes.filter { note -> note.alarmId == _alarmId }
    }
}