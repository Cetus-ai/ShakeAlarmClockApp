package com.griffith.shakealarmclockapp.data

import java.util.UUID


data class Note(
    val alarmId: String,
    val text: String,
    val hour: Int,
    val minute: Int
)