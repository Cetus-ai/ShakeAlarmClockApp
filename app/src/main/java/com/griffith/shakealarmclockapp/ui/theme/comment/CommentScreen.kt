package com.griffith.shakealarmclockapp.ui.theme.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.util.Calendar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
fun CommentForm(
    //noteList: List<Note>
){
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
                .padding(paddingValues)
        ){
            Text(
                text = "Reminder",
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Row {
                TimeInput(
                    state = timePickerState,
                )
            }
        }
    }
}

//Column (
//modifier = Modifier
//.fillMaxWidth()
//){
//    Text(
//        text = "Reminder",
//        fontSize = 30.sp,
//        color = Color.White,
//        fontWeight = FontWeight.Bold
//    )
//
//    LazyColumn (
//        modifier = Modifier
//            .fillMaxSize()
//    ){
//        items(alarmsListing){ _alarm ->
//            AlarmItem(
//                alarm = _alarm,
//                onToggle = {onToggleAlarm(_alarm)}
//            )
//            Spacer(modifier = Modifier.height(12.dp))
////                    Alarm(
////                        alarm.id,
////                        alarm.name,
////                        alarm.hour,
////                        alarm.minute,
////                        alarm.isEnable,
////                        days = alarm.days
////                    )
////                    Text("id: " + alarm.id + "\nname: " + alarm.name + "\n" + alarm.hour + ":" + alarm.minute + "\nOn/Off: " + alarm.isEnable + "\nDays: " + alarm.days)
//        }
//    }
//}
////        TimeInput(
////            state = timePickerState,
////        )