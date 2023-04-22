package com.nangosha.smarthome.main_screens

import android.annotation.SuppressLint
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController,  viewModel: SmartHomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val routines = uiState.routines

    Scaffold(
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight(0.8F).fillMaxWidth()
            ) {
                    Box(
                        modifier = Modifier
                        //.fillMaxWidth(),
                        // .padding(top = 150.dp),
                        //contentAlignment = Alignment.TopCenter
                    ){
                        Icon(
                            painterResource(R.drawable.favorites_bar_icon), contentDescription = null, tint = Color.Gray,
                            modifier = Modifier.size(180.dp).padding(bottom = 10.dp))
                    }

                    Text(text = "No Favorites!", color = Color.Gray,
                        fontSize = 30.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(text = "Add your favorite routines for easy access here", color = Color.Gray,
                        fontSize = 18.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(text = "Tap the '+' button below to add your favorite routines." , color = Color.Gray,
                        fontSize = 18.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                        //modifier = Modifier.fillMaxWidth()
                    )
            }
        },
        topBar = { HomeTopBar() },
        floatingActionButton = {FloatingActionButton(onClick = { navController.navigate(Screen.SelectCreateRoutine.route) },
            backgroundColor = Color.Cyan, contentColor = Color.White) {
            Icon(Icons.Filled.Add, contentDescription = "Add favorite.")
        }},
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomBar(navController = navController) }
    )
}

