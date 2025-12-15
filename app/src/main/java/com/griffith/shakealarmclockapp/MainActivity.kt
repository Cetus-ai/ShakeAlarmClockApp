package com.griffith.shakealarmclockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.griffith.shakealarmclockapp.navigation.NavGraph
import com.griffith.shakealarmclockapp.ui.theme.ShakeAlarmClockAppTheme
import com.griffith.shakealarmclockapp.utils.PermissionHelper

class MainActivity : ComponentActivity() {

    lateinit var permissionHelper: PermissionHelper

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionHelper = PermissionHelper(this)
        permissionHelper.askingPermission()

        setContent {
            ShakeAlarmClockAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}