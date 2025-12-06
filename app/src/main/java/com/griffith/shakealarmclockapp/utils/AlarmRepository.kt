package com.griffith.shakealarmclockapp.utils

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AlarmRepository {

    val db: FirebaseFirestore = Firebase.firestore
}