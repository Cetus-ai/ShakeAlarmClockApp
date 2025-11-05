package com.griffith.shakealarmclockapp.ui.theme.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.data.Note
import com.griffith.shakealarmclockapp.ui.theme.home.AlarmItem
import java.util.Calendar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview(backgroundColor = 0xFF494444, showBackground = true)
fun CommentForm(
    alarmId: String,
    noteList: List<Note>,
    onCancel: () -> Unit,
    onSafeNoteClick: (String, String, Int, Int) -> Unit
){
    var noteText by remember { mutableStateOf("") }
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    Scaffold { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                TextButton(onClick = onCancel)
                { Text("Cancel", color = Color.Blue, fontSize = 18.sp)}
                Text(
                    text = "Reminder",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = {
                    onSafeNoteClick(
                        alarmId,
                        noteText,
                        timePickerState.hour,
                        timePickerState.minute
                    )
                    noteText = ""
                }) {
                    Text("Save", color = Color.Blue, fontSize = 18.sp)
                }
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TimeInput(
                    state = timePickerState,
                    modifier = Modifier
                        .scale(0.8f)
                )
                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    label = {Text("Write your reminder her")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                        .padding(top = 20.dp),
                    maxLines = 5
                )

                LazyColumn (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ){
                    items(noteList){ _note ->
                        NoteItem(
                            note = _note
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}