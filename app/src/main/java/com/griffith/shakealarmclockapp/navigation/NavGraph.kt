package com.griffith.shakealarmclockapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.griffith.shakealarmclockapp.ui.theme.home.HomeScreen
import com.griffith.shakealarmclockapp.ui.theme.create.CreateAlarm
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel
import com.griffith.shakealarmclockapp.ui.theme.comment.CommentForm


@Composable
fun NavGraph(
    navController: NavHostController
){
    //val navController = rememberNavController()
    val viewmodel = remember { AlarmViewModel() }


    NavHost(navController = navController, startDestination = "home"){

        composable("home"){
            HomeScreen(
                alarmsListing = viewmodel.alarms,
                onAddAlarmClick = { navController.navigate(route = "create")},
                onToggleAlarm = {_alarm -> viewmodel.toggleAlarm(_alarm.id)},
                editAlarm = {navController.navigate(route = "note")}
            )
        }

        composable("create"){
            CreateAlarm(
                onCancel = {navController.navigate(route = "home")},
                onSafeAlarmClick = { name, hour, minute, _, days ->
                    viewmodel.addAlarm(
                        _name = name, _hour = hour, _minute = minute, _isEnable = true, days
                    )
                    navController.navigate("home")
                }
            )
        }


        composable ("note"){
            CommentForm(

            )
        }
    }
}