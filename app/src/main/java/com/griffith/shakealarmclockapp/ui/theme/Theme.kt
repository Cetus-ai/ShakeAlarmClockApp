package com.griffith.shakealarmclockapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


// Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = AppPurple,
    secondary = AppBlue,
    tertiary = AppOrange,
    background = AppBackgroundDark,
    surface = AppBackgroundGray,
    onPrimary = AppTextWhite,
    onSecondary = AppTextWhite,
    onBackground = AppTextWhite,
    onSurface = AppTextWhite
)

// Light Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = AppPurple,
    secondary = AppBlue,
    tertiary = AppOrange,
    background = Color.White,
    surface = Color.LightGray,
    onPrimary = AppTextWhite,
    onSecondary = AppTextWhite,
    onBackground = AppTextBlack,
    onSurface = AppTextBlack
)

@Composable
fun ShakeAlarmClockAppTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme){
        DarkColorScheme
    } else{
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}