package com.griffith.shakealarmclockapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
//    val commentScreen = remember { CommentScreen() }

    NavHost(navController = navController, startDestination = "home"){

        composable("home"){
            HomeScreen(
                alarmsListing = viewmodel.alarms,
                onAddAlarmClick = { navController.navigate(route = "create")},
                onToggleAlarm = {_alarm -> viewmodel.toggleAlarm(_alarm.id)},
                editAlarm = { alarm ->
                    navController.navigate(route = "note/${alarm.id}")
                }
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


        composable ("note/{alarmId}"){ backStackEntry ->
            val alarmId = backStackEntry.arguments?.getString("alarmId") ?: ""
            CommentForm(
                alarmId = alarmId,
                noteList = viewmodel.notes,
                onSafeNoteClick = { alarmId, text, hour, minute ->
                    viewmodel.addNote(
                        _alarmId = alarmId, _text = text, _hour = hour, minute
                    )
//                    navController.navigate("home")
                },
                onCancel = {navController.navigate(route = "home")}
            )
        }
    }
}