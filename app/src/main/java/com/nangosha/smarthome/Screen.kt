package com.nangosha.smarthome

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("favorites", R.string.favorites)
    object Things : Screen("things", R.string.things)
    object Routines : Screen("routines", R.string.routines)
    object Ideas : Screen("ideas", R.string.ideas)
    object Settings : Screen("settings", R.string.settings)
    object SelectCreateRoutine: Screen("select_create_routine", R.string.select_create_routine)
    object CreateRoutine: Screen("create_routine", R.string.create_routine)
    object CreateEvent: Screen("create_event", R.string.create_event)
    object CreateAction: Screen("create_action", R.string.create_action)

}