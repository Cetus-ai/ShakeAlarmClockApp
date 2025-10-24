package com.griffith.shakealarmclockapp.viewmodel

import androidx.lifecycle.ViewModel
import com.griffith.shakealarmclockapp.data.Alarm

class AlarmViewModel : ViewModel(){
    val alarms = mutableListOf<Alarm>()

    fun addAlarm(_name: String, _hour: Int, _minute: Int, _isEnable: Boolean ){
        val newAlarm = Alarm(
            name = _name,
            hour = _hour,
            minute = _minute,
            isEnable = _isEnable
        )
        alarms.add(newAlarm)
    }

//val id: String = UUID.randomUUID().toString(),
//val name: String,
//val hour: Int,
//val minute: Int,
//val isEnable: Boolean
}