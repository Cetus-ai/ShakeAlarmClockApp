package com.griffith.shakealarmclockapp.ui.theme.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TitelInputbox(
    titel: String,
    value: String,
    onValueChange: (String) -> Unit
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
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {Text("Minutes")}
            )
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun TitelSlidebar(
    titel: String,
    value: Float,
    onValueChange: (Float) -> Unit
){
    //var sliderPostion by remember { mutableStateOf(0f) }
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
            Slider(
                value = value,
                onValueChange = onValueChange,
                valueRange = 0f..100f
            )
        }

    }
}

@Composable
fun TitelSubtitle(
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
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
            )
        }
    }
}