package com.griffith.shakealarmclockapp.ui.theme.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SettingsItem(
    title: String,
    subtitle: String,
){
    Card (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column {
            Text(
                text = title,
                color = Color.White
            )
            Text(
                text = subtitle,
            )
        }
    }
}