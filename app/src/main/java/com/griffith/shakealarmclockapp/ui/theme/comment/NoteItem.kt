package com.griffith.shakealarmclockapp.ui.theme.comment

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.data.Note

//@Preview
@Composable
fun NoteItem(
    note: Note
){
    val note = Note(
        text = "Time for ScoobySnacks",
        hour = 3,
        minute = 0
    )
    Card (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row {
            Text(
                text = String.format("%02d:%02d", note.hour, note.minute),
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = note.text,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}