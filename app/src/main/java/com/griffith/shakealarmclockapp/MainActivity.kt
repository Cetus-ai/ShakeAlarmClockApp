package com.griffith.shakealarmclockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.griffith.shakealarmclockapp.navigation.NavGraph


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Color(0xFF2C2C2E)          //Backgroundcolor of the entire Application
                )
            ){
                val navController = rememberNavController()        //Creating a navController
                NavGraph(navController = navController)            //Starting the NavGraph
            }
        }
    }
}