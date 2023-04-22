package com.nangosha.smarthome.main_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nangosha.smarthome.BottomBar
import com.nangosha.smarthome.IdeasScreenTopBar
import com.nangosha.smarthome.R
import com.nangosha.smarthome.ui.theme.CustomBackground
import com.nangosha.smarthome.ui.theme.CustomYellow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen (navController: NavController){
    Scaffold(
        content = {
            Column(verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(0.9F)
                // todo fill the column to the screen without using a hardcoded value like 0.9F
                // todo add a divider between the rows, make this thing scrollable,

        ){
            SettingTitleBar(title = "User settings")

            Row(modifier =  Modifier.fillMaxWidth(),
            ) {
                Column(verticalArrangement = Arrangement.Center){
                    Icon(
                        painterResource(R.drawable.account_circle), contentDescription = null, tint = CustomYellow,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .size(40.dp)
                    )
                }
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.Center
                ){
                    Text("Moses", fontSize = 20.sp)
                    Text("kmntanda@gmail.com", color = Color.Gray)
                }
            }

            SettingTitleBar(title = "Home settings")
            SettingItemBar(content = "Accounts & Hubs", icon = R.drawable.person)
            SettingItemBar(content = "Clients", icon = R.drawable.group)
            SettingItemBar(content = "Locations", icon = R.drawable.location_on)
            SettingTitleBar(title = "Voice")
            SettingItemBar(content = "Voice Assistants", icon = R.drawable.voice)
            SettingTitleBar(title = "App permissions")
            SettingItemBar(content = "Notifications & Permissions", icon = R.drawable.settings)
            }
      },
        topBar = { IdeasScreenTopBar() },
        bottomBar = { BottomBar(navController) },
    )
}

@Composable
fun SettingTitleBar(title: String){
    Text(text = title,
        fontSize = 20.sp, color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 0.dp)
            .background(CustomBackground)
            .padding(horizontal = 10.dp, vertical = 5.dp))
}

@Composable
fun SettingItemBar(content: String, icon: Int){
    Row {
        Icon(painterResource(icon), contentDescription = null, tint = CustomYellow,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .size(40.dp)
        )
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ){
            Text(content, fontSize = 20.sp)
        }
    }
}