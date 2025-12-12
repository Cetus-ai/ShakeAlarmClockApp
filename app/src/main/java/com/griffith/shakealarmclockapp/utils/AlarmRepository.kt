package com.griffith.shakealarmclockapp.utils

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.griffith.shakealarmclockapp.data.Alarm
import com.griffith.shakealarmclockapp.data.Note
import com.griffith.shakealarmclockapp.ui.theme.home.AlarmItem
import kotlinx.coroutines.tasks.await

class AlarmRepository {

    val db: FirebaseFirestore = Firebase.firestore                                                  //Access to FireBase
    val alarmsDoc = db.collection("app_data").document("alarms")       //Find/Create collection "app_data" and take document "alarms"
    val settingsDoc = db.collection("app_data").document("settings")

    suspend fun saveAllAlarm(alarms: List<Alarm>){                                                  //asynch: Must wait for response from FireBase
        val data = hashMapOf("alarms" to alarms)
        alarmsDoc.set(data).await()
    }

    suspend fun saveAllSettings(shakeIntensity: Float, alarmVolumn: Float){
        val data = hashMapOf(
            "shakeIntensity" to shakeIntensity,
            "alarmVolume" to alarmVolumn
        )
        settingsDoc.set(data).await()
    }

    suspend fun loadAllAlarms(): Result<List<Alarm>> {                                              //reading data from Firebase and combine it into alarms
        return try {
            val snapshot = alarmsDoc.get().await()
            val alarmsList = snapshot.get("alarms") as? List<Map<String, Any>> ?: emptyList()       //FireBase always keep a Key as String, but the alarm has different data types (=any)

            val alarms = alarmsList.map { alarmMap ->
                Alarm(
                    id = alarmMap["id"] as? String ?: "",
                    name = alarmMap["name"] as? String ?: "",
                    hour = (alarmMap["hour"] as? Long)?.toInt() ?: 0,
                    minute = (alarmMap["minute"] as? Long)?.toInt() ?: 0,
                    isEnable = alarmMap["isEnable"] as? Boolean ?: true,
                    days = (alarmMap["days"] as? List<String>) ?: emptyList(),
                    notes = (alarmMap["notes"] as? List<Map<String, Any>>)?.map { noteMap ->
                        Note(
                            alarmId = alarmMap["id"] as? String ?: "",
                            text = noteMap["text"] as? String ?: "",
                            hour = (noteMap["hour"] as? Long)?.toInt() ?: 0,
                            minute = (noteMap["minute"] as? Long)?.toInt() ?: 0
                        )
                    } ?: emptyList()
                )
            }

            Result.success(alarms)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loadSettings(): Result<Pair<Float, Float>> {
        return try {
            val snapshot = settingsDoc.get().await()
            val shakeIntensity = snapshot.get("shakeIntensity") as? Double ?: 15.0
            val alarmVolume = snapshot.get("alarmVolume") as? Double ?: 50.0
            Result.success(Pair(shakeIntensity.toFloat(), alarmVolume.toFloat()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}