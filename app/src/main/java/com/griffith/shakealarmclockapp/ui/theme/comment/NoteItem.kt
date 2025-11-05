package com.griffith.shakealarmclockapp.ui.theme.comment

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.data.Note

@Composable
fun NoteItem(
    note: Note,
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row {
            Text(
                text = String.format("%02d:%02d", note.hour, note.minute),                        //Defines the format in which the time should be displayed in the note
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = note.text,                                                                 //During the iteration each note get the necessary Data which is define in Note. Here is the output of the attribute text from Note.
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}