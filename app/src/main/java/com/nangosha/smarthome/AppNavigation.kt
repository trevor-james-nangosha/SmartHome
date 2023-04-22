package com.nangosha.smarthome

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nangosha.smarthome.main_screens.HomeScreen
import com.nangosha.smarthome.main_screens.SettingsScreen
import com.nangosha.smarthome.main_screens.ThingsScreen
import com.nangosha.smarthome.routines.CreateRoutines
import com.nangosha.smarthome.routines.RoutinesScreen
import com.nangosha.smarthome.routines.SelectCreateRoutine

@Composable
fun AppNavigation (viewModel: SmartHomeViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController, viewModel) }
        composable(Screen.Things.route) { ThingsScreen(navController) }
        composable(Screen.Routines.route) { RoutinesScreen(navController, viewModel) }
        composable(Screen.Ideas.route) { IdeasScreen(navController) }
        composable(Screen.Settings.route) { SettingsScreen(navController) }
        composable(Screen.SelectCreateRoutine.route) { SelectCreateRoutine(navController, viewModel) }
        composable(Screen.CreateRoutine.route) { CreateRoutines(navController, viewModel = viewModel) }
        composable(Screen.CreateEvent.route) { CreateEvents(navController, viewModel) }
        composable(Screen.CreateAction.route) { CreateActions(navController, viewModel) }
    }
}