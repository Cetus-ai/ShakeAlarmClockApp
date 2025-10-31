package com.griffith.shakealarmclockapp.viewmodel

import androidx.lifecycle.ViewModel
import com.griffith.shakealarmclockapp.data.Alarm

class AlarmViewModel : ViewModel(){
    val alarms = mutableListOf<Alarm>()

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
}