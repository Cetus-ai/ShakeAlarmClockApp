package com.griffith.shakealarmclockapp.ui.theme.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.griffith.shakealarmclockapp.data.Alarm

@SuppressLint("DefaultLocale")
//@Preview
@Composable
fun AlarmItem(
    alarm: Alarm,
    onToggle: () -> Unit,
    edit: () -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    Card (
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray,
        )
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Text(
                    text = alarm.name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = String.format("%02d:%02d", alarm.hour, alarm.minute),
                    fontSize = 30.sp,
                    color = Color.White
                )
                LazyRow (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        //.fillMaxWidth()
                ){
                    items(alarm.days){ day ->
                        Text(
                            text = day,
                            color = Color(0xFFFFA500),
                            fontSize = 16.sp,
                        )
                    }
                }
            }
//            Spacer(modifier = Modifier.width(100.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy((30).dp),
            ){
                Switch(
                    checked = alarm.isEnable,
                    onCheckedChange = { onToggle() }
                )
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                ){
                    IconButton(onClick = {expanded = !expanded}) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Config")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {expanded = false}
                    ) {
                        DropdownMenuItem(
                            text = {Text("Add Reminder")},
                            onClick = {edit()}
                        )
                        DropdownMenuItem(
                            text = {Text("Edit Alarm")},
                            onClick = {}                                    //TODO:
                        )
                        DropdownMenuItem(
                            text = {Text("Remove Alarm")},
                            onClick = {}                                    //TODO:
                        )
                    }
                }
//                IconButton(onClick = {edit() }) {
//                    Icon(
//                        Icons.Default.MoreVert,
//                        contentDescription = "Config",
//                        tint = Color.White
//                    )
//                }
            }
        }
    }
}