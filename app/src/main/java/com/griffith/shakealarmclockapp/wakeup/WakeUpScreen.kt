package com.griffith.shakealarmclockapp.wakeup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//WakeUpScreen when the alarm ring
fun WakeUpScreen(
    alarmId: String?,
    viewModel: AlarmViewModel,
    onDismiss: () -> Unit,
    onSnooze: (Int) -> Unit
) {
    val notes = viewModel.filterNotes(alarmId ?: "")

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Wake Up",
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (notes.isNotEmpty()) {
                Text(
                    text = "Your Reminder:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(notes) { note ->
                        ReminderItem(note = note)
                    }
                }
            } else {
                Text(
                    text = "No Reminder for today",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}