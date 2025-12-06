package com.griffith.shakealarmclockapp.data

//Here are all the attributes defined that are needed to create an object of type Note.
data class Note(
    val alarmId: String,
    val text: String,
    val hour: Int,
    val minute: Int
)