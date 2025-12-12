package com.griffith.shakealarmclockapp.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.griffith.shakealarmclockapp.alarm.AlarmScheduler
import com.griffith.shakealarmclockapp.data.Alarm
import com.griffith.shakealarmclockapp.data.Note
import com.griffith.shakealarmclockapp.utils.AlarmRepository
import kotlinx.coroutines.launch

class AlarmViewModel(
    val app: Application,
    val savedState: SavedStateHandle

): ViewModel(){
    val repository = AlarmRepository()
    val alarms = mutableStateListOf<Alarm>()
    val notes = mutableStateListOf<Note>()
    val scheduler = AlarmScheduler(app.applicationContext)
    var shakeIntensity by mutableStateOf(15.0F)
    var alarmVolume by mutableStateOf(50.0F)
    var ShakeIntensityProp: Float                                                                         //This is the property for the Shake intensity, the values are set in SettingScreen.kt
        get() {
            return shakeIntensity
        }
        set(value) {
            shakeIntensity = value
        }

    var AlarmVolumeProp: Float                                                                          //This is the property for the Alarm Volume, the values are set in SettingScreen.kt
        get() {
            return alarmVolume
        }
        set(value) {
            alarmVolume = value
        }

    init {                                                                                          //Initializeblock, alarms will be loaded in the same Moment the ViewModel is created
        loadAlarms()
    }

    fun loadAlarms() {                                                                              //load Alarms from FireBase
        viewModelScope.launch {                                                                     //starting a Coroutine = background task
            val result = repository.loadAllAlarms()
            result.onSuccess { loadAlarms ->
                alarms.clear()
                notes.clear()
                alarms.addAll(loadAlarms)
                loadAlarms.forEach { alarm ->
                    notes.addAll(alarm.notes)

                    if(alarm.isEnable){
                        scheduler.scheduleAlarm(alarm.id, alarm.hour, alarm.minute, alarm.days)
                    }
                }
            }
        }
    }

    fun safeAlarms(){                                                                               //safe Alarms in FireBase
        viewModelScope.launch {                                                                     //starting as a background Task
            val alarmsWithNotes = alarms.map { alarm ->
                val alarmNotes = notes.filter { it.alarmId == alarm.id }
                alarm.copy(notes = alarmNotes)
            }
            repository.saveAllAlarm(alarmsWithNotes)
        }
    }

    companion object{
        // Factory to create AlarmViewModel with required dependencies:
        // - Application: provides context for AlarmScheduler (and incoming database)
        // - SavedStateHandle: preserves state across process death
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <H : ViewModel> create(
                modelClass: Class<H>,
                extras: CreationExtras
            ): H {
                val application = checkNotNull(extras[APPLICATION_KEY]) as Application                  //To build a stable context of a ViewModel and get all dependencys

                val savedStateHandle = extras.createSavedStateHandle()

                return AlarmViewModel(
                    app = application,
                    savedState = savedStateHandle
                ) as H
            }
        }
    }

    fun addAlarm(_name: String, _hour: Int, _minute: Int, _isEnable: Boolean, _days: List<String> ){    //Creating and adding Alarm to the List alarms after the user created it in CreateAlarmScreen.kt
        val newAlarm = Alarm(
            name = _name,
            hour = _hour,
            minute = _minute,
            isEnable = _isEnable,
            days = _days,
            notes = emptyList()
        )
        alarms.add(newAlarm)
        scheduler.scheduleAlarm(newAlarm.id, newAlarm.hour, newAlarm.minute, _days)

        safeAlarms()
    }

    fun toggleAlarm(alarmId: String){                                                                   //The ViewModel dont have any direct access to the objects once they are "sended" to other activitys. Like a headquarter in a delivery company.
        val index = alarms.indexOfFirst { it.id == alarmId }                                            //That means if i want to change a alarm object here, i have to create a new one which is based on the old object but with my modifications.
        if (index != -1) {                                                                              //So I created a new one by coping the old alarm. Just this time with another togglestatus
            val alarm = alarms[index]
            val newState = !alarm.isEnable
            val updatedAlarm = alarm.copy(isEnable = newState)
            val days = alarm.days
            if(updatedAlarm.isEnable){
                scheduler.scheduleAlarm(updatedAlarm.id, updatedAlarm.hour, updatedAlarm.minute, days)
            }else{
                scheduler.cancelAlarm(alarmId)
            }
            alarms[index] = updatedAlarm

        }
        safeAlarms()
    }

    fun addNote(_alarmId: String, _text: String, _hour: Int, _minute: Int){                             //Creating and adding Note to the List notes after the user created it in CommentScreen.kt
        val note = Note(
            _alarmId,
            text = _text,
            _hour,
            _minute
        )
        notes.add(note)
        safeAlarms()
    }

    fun filterNotes(_alarmId: String): List<Note>{                                                      //iterating through all the created notes and checking the id. With .filter, i return a List<Note> of all the notes which matched the alarmId
        return notes.filter { note -> note.alarmId == _alarmId }
    }

    fun deleteAlarm(_alarmId: String){
        val index = alarms.indexOfFirst { it.id == _alarmId }
        if (index != -1) {
//            val alarm = alarms[index]
            scheduler.cancelAlarm(_alarmId)
            alarms.removeAt(index)
        }
        safeAlarms()
    }

    fun updateAlarm(_alarmId: String, _name: String, _hour: Int, _minute: Int, _days: List<String>){
        val index = alarms.indexOfFirst { it.id == _alarmId }
        if(index != -1){
            val oldAlarm = alarms[index]

            scheduler.cancelAlarm(_alarmId)

            val updatedAlarm = oldAlarm.copy(
                name = _name,
                hour = _hour,
                minute = _minute,
                days = _days
            )

            alarms[index] = updatedAlarm

            if(updatedAlarm.isEnable){
                scheduler.scheduleAlarm(updatedAlarm.id, updatedAlarm.hour, updatedAlarm.minute, _days)
            }
        }
        safeAlarms()
    }
}