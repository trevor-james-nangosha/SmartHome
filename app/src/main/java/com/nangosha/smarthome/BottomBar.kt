package com.nangosha.smarthome

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nangosha.smarthome.ui.theme.CustomGrey
import com.nangosha.smarthome.ui.theme.CustomYellow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Things,
        Screen.Routines,
        Screen.Ideas,
        Screen.Settings,
    )

    val iconIds = listOf(
        R.drawable.favorites_bar_icon,
        R.drawable.things_bar_icon,
        R.drawable.routines_bar_icon,
        R.drawable.ideas_bar_icon,
        R.drawable.settings_bar_icon
    )

    BottomAppBar(
        backgroundColor = CustomGrey,
        contentPadding = PaddingValues(start = 0.dp, end = 0.dp),
    ) {
        BottomNavigation(
            backgroundColor = CustomGrey,
            modifier = Modifier
                .padding(start = 0.dp, end = 0.dp)
                .fillMaxWidth()
                .border(0.dp, color = CustomGrey)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                val isVisitingCurrentScreen = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                val iconId = iconIds[items.indexOf(screen)]
                BottomNavigationItem(
                    icon = { Icon(
                        painterResource(iconId), contentDescription = null,
                        tint = if(isVisitingCurrentScreen){CustomYellow} else {Color.Gray},
                        modifier = Modifier.size(30.dp)) },
                    label = { Text(stringResource(screen.resourceId),
                        color=if(isVisitingCurrentScreen){CustomYellow} else {Color.Black},
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    ) },
                    selected = isVisitingCurrentScreen,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = false // set this flag to true if you want problems
                                inclusive = true // todo, remove this line in case shit backfires. Apparently, it is okay.
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}


