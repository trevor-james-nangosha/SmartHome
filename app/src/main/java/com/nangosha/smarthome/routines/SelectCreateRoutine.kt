package com.nangosha.smarthome.routines

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nangosha.smarthome.Screen
import com.nangosha.smarthome.SmartHomeViewModel
import com.nangosha.smarthome.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SelectCreateRoutine(navController: NavController, viewModel: SmartHomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val routines = uiState.routines

    Scaffold(
        content = {
            Column {
                Row (modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 10.dp)){
                    Button(onClick = { navController.navigate(Screen.CreateRoutine.route) },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan, contentColor = Color.White)
                    ) {
                        Icon(
                            Icons.Default.Add, contentDescription = "Create a new Routine", tint = Color.White,
                            modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp).size(25.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                    ){
                        Text("Create a new Routine",  fontSize = 20.sp)
                    }
                }
                if (routines.isNotEmpty()){
                    routines.forEach {routine ->
                        RoutineItem(name = routine.name, lastRun = routine.lastRun, showLastRun = false)
                    }
                }
            }

        },
        topBar = {TopBar("Select a routine", back = true)},

    )
}