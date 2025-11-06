package com.griffith.shakealarmclockapp.data

import java.util.UUID

//Here are all the attributes defined that are needed to create an object of type Alarm.
data class Alarm(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val hour: Int,
    val minute: Int,
    val isEnable: Boolean,
    val days: List<String>
)