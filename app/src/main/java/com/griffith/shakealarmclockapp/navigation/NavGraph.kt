package com.griffith.shakealarmclockapp.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.griffith.shakealarmclockapp.ui.theme.home.HomeScreen
import com.griffith.shakealarmclockapp.ui.theme.create.CreateAlarm
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel
import com.griffith.shakealarmclockapp.ui.theme.comment.CommentScreen
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun NavGraph(
    navController: NavHostController
){
    val viewmodel: AlarmViewModel = viewModel(
        factory = AlarmViewModel.Factory
    )
//    val app = LocalContext.current.applicationContext as Application
//    val viewmodel = remember { AlarmViewModel() }                                                       //Creating a object from type AlarmViewModel to gain access of his functions

    NavHost(navController = navController, startDestination = "home"){                                  //From where the NavController will navigate, the Start

        composable("home"){                                                                     //Callback implementation for the HomeScreen
            HomeScreen(
                alarmsListing = viewmodel.alarms,
                onAddAlarmClick = { navController.navigate(route = "create")},
                onToggleAlarm = {_alarm -> viewmodel.toggleAlarm(_alarm.id)},
                editAlarm = { alarm ->
                    navController.navigate(route = "note/${alarm.id}")
                }
            )
        }

        composable("create"){                                                                   //Callback implementation for the CreateAlarmScreen
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


        composable ("note/{alarmId}"){ backStackEntry ->                                        ////Callback implementation for the CommentScreen
            val alarmId = backStackEntry.arguments?.getString("alarmId") ?: ""
            CommentScreen(
                alarmId = alarmId,
                noteList = viewmodel.filterNotes(alarmId),
                onSafeNoteClick = { alarmId, text, hour, minute ->
                    viewmodel.addNote(
                        _alarmId = alarmId, _text = text, _hour = hour, minute
                    )
                },
                onCancel = {navController.navigate(route = "home")}
            )
        }
    }
}