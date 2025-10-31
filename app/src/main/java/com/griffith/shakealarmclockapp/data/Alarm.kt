package com.griffith.shakealarmclockapp.data

import java.util.UUID

data class Alarm(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val hour: Int,
    val minute: Int,
    val isEnable: Boolean,
    val days: List<String>
)