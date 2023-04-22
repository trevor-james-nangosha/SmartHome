package com.nangosha.smarthome

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateEvents(navController: NavController, viewModel: SmartHomeViewModel){
    Scaffold(
        content = {
                  Column(
                      modifier = Modifier.padding(horizontal = 10.dp)
                  ) {
                      LocalContext.current

                      Row(modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = {
                          viewModel.updateShowTimePicker(true)
                          navController.navigate(Screen.CreateRoutine.route)
                      })){
                          Text("The time is Time", fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                          )
                      }
                      Divider (color = Color.Gray, modifier = Modifier.height(1.dp).fillMaxHeight().fillMaxWidth())

                      Row(modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = {})){
                          Text("It's sunset at Location", fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp, top = 10.dp))
                      }
                      Divider (color = Color.Gray, modifier = Modifier.height(1.dp).fillMaxHeight().fillMaxWidth())

                      Row(modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = {})){
                          Text("It's sunrise at Location", fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp, top = 10.dp))
                      }
                      Divider (color = Color.Gray, modifier = Modifier.height(1.dp).fillMaxHeight().fillMaxWidth())

                      Row(modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = {})){
                          Text("It's 15 minutes before sunrise at Location", fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp, top = 10.dp))
                      }
                      Divider (color = Color.Gray, modifier = Modifier.height(1.dp).fillMaxHeight().fillMaxWidth())

                      Row(modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = {})){
                          Text("It's 15 minutes after sunrise at Location", fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp, top = 10.dp))
                      }
                      Divider (color = Color.Gray, modifier = Modifier.height(1.dp).fillMaxHeight().fillMaxWidth())

                      Row(modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = {})){
                          Text("It's 15 minutes before sunset at Location", fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp, top = 10.dp))
                      }
                      Divider (color = Color.Gray, modifier = Modifier.height(1.dp).fillMaxHeight().fillMaxWidth())

                      Row(modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = {})){
                          Text("It's 15 minutes after sunset at Location", fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp, top = 10.dp))
                      }
                  }
        },
        topBar = { TopBar(title = "Select an event", back = true) }
    )

}