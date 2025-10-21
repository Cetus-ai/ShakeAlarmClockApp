package com.griffith.shakealarmclockapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.griffith.shakealarmclockapp.ui.theme.create.Test
import com.griffith.shakealarmclockapp.ui.theme.home.HomeScreen

@Composable
fun NavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){

        composable("home"){
            HomeScreen(                     //<--- if Button click, navigator guides to "create"
                addAlarmClick = { navController.navigate(route = "create")}
            )
        }

        composable("create"){
            Test();
        }
    }
}