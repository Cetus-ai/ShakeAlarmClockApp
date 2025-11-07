package com.griffith.shakealarmclockapp.ui.theme.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

//@Composable
//fun SettingsItem(
//    title: String,
//    subtitle: String,
//){
//    Card (
//        modifier = Modifier
//            .fillMaxSize()
//    ){
//        Column {
//            Text(
//                text = title,
//                color = Color.White,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = subtitle,
//            )
//        }
//    }
//}

@Composable
fun TitelInputbox(
    titel: String,
    value: Int
){
    Card(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column {
            Text(
                text = titel,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

    }
}