package com.griffith.shakealarmclockapp.data

data class Note(
    val alarmId: String,
    val text: String,
    val hour: Int,
    val minute: Int
)