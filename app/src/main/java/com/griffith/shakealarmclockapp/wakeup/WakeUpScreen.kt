package com.griffith.shakealarmclockapp.wakeup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
        }
    }
}