package com.griffith.shakealarmclockapp.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.griffith.shakealarmclockapp.data.Alarm
import com.griffith.shakealarmclockapp.data.Note

class AlarmViewModel(
    val app: Application,
    val savedState: SavedStateHandle
){
    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY]) as Application

                val savedStateHandle = extras.createSavedStateHandle()

                return AlarmViewModel(
                    app = application,
                    savedState = savedStateHandle
                ) as T
            }
        }
    }


    val alarms = mutableStateListOf<Alarm>()
    val notes = mutableStateListOf<Note>()

    var snoozerDuration by mutableStateOf(5)
    var alarmVolume by mutableStateOf(50.0F)
    var SnoozeDurationProp: Int                                                                         //This is the property for the Snoozer duration, the values are set in SettingScreen.kt
        get() {
            return snoozerDuration
        }
        set(value) {
            if(value > 5)
                SnoozeDurationProp = 5
            snoozerDuration = value
        }

    var AlarmVolumeProp: Float                                                                          //This is the property for the Alarm Volume, the values are set in SettingScreen.kt
        get() {
            return alarmVolume
        }
        set(value) {
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
//        scheduler
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