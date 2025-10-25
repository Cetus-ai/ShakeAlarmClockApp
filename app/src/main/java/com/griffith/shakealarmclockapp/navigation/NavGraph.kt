package com.griffith.shakealarmclockapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.griffith.shakealarmclockapp.ui.theme.home.HomeScreen
import com.griffith.shakealarmclockapp.ui.theme.create.CreateAlarm
import com.griffith.shakealarmclockapp.viewmodel.AlarmViewModel
import com.griffith.shakealarmclockapp.ui.theme.home.AlarmItem


@Composable
fun NavGraph(
    navController: NavHostController
){
    //val navController = rememberNavController()
    val viewmodel = remember { AlarmViewModel() }


    NavHost(navController = navController, startDestination = "home"){

//        composable<home>{                 //Safe-Navigation
//            HomeScreen(
//                addAlarmClick = {
//                    navController.navigate(route = "create")
//                }
//            )
//        }
        composable("home"){          //string-Navigation
            HomeScreen(                     //<--- if Button click, navigator guides to "create"
                alarmsListing = viewmodel.alarms,
                onAddAlarmClick = { navController.navigate(route = "create")},
            )
        }

        composable("create"){
            CreateAlarm(
                onCancel = {navController.navigate(route = "home")},
                onSafeAlarmClick = { name, hour, minute, _ ->
                    viewmodel.addAlarm(
                        _name = name, _hour = hour, _minute = minute, _isEnable = true, List<String>
                    )
                    navController.navigate("home");
                }
            )
        }
    }
}