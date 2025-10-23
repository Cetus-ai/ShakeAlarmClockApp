package com.griffith.shakealarmclockapp.data

import androidx.room.Entity
import java.util.UUID

@Entity
data class Alarm(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val hour: Int,
    val minute: Int,
    val isEnable: Boolean
)