package com.nangosha.smarthome.routines

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nangosha.smarthome.*
import com.nangosha.smarthome.R
import com.nangosha.smarthome.ui.theme.CustomBackground

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RoutinesScreen(navController: NavController, viewModel: SmartHomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val routines = uiState.routines

    Scaffold(
        content = {
            if(routines.isEmpty()){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight(0.9F)
                        .fillMaxWidth()
                    // todo; make this thing scrollable
                ){
                    if (routines.isEmpty()){
                        Box{
                            Icon(
                                painterResource(R.drawable.routines_bar_icon), contentDescription = null, tint = Color.Gray,
                                modifier = Modifier.size(180.dp))
                        }

                        Text(text = "No Routines!", color = Color.Gray,
                            fontSize = 30.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                        )
                        Text(text = "Click the '+' button below to get started.", color = Color.Gray,
                            fontSize = 15.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }else if (routines.isNotEmpty()){
                Column() {
                    Text(text = "Active",
                        fontSize = 20.sp, color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp, top = 0.dp)
                            .background(CustomBackground)
                            .padding(horizontal = 15.dp, vertical = 15.dp)
                    )

                    routines.forEach {routine ->
                        RoutineItem(name = routine.name, lastRun = routine.lastRun)
                    }
                }
            }
        },
        topBar = { RoutinesTopBar() },
        floatingActionButton = {FloatingActionButton(onClick = { /*do something*/ }, backgroundColor = Color.Cyan, contentColor = Color.White) {
            Icon(Icons.Filled.Add, contentDescription = "Add favorite.")
        }},
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomBar(navController) }
    )
}