package com.griffith.shakealarmclockapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.griffith.shakealarmclockapp.ui.theme.home.HomeScreen
import com.griffith.shakealarmclockapp.ui.theme.create.CreateAlarm
import com.griffith.shakealarmclockapp.viewmodel.onSafeAlarmClick

@Composable
fun NavGraph(
    navController: NavHostController
){
    //val navController = rememberNavController()

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
                onAddAlarmClick = { navController.navigate(route = "create")}
            )
        }

        composable("create"){
            CreateAlarm(
                onCancel = {navController.navigate(route = "home")},
                onSafeAlarmClick {name, hour, }
            )
        }
    }
}