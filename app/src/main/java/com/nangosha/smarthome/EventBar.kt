package com.nangosha.smarthome

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventBar(time: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row{
            Icon(painterResource(R.drawable.schedule), contentDescription = null, tint = Color.Black)
            Column{
                Text("Date & Time", color= Color.Gray)
                Text("The time is $time", fontSize = 18.sp)
            }
        }

        Icon(painterResource(R.drawable.settings), contentDescription = null, tint = Color.Gray, modifier = Modifier.size(46.dp))
    }
}

@Composable
fun NewNotificationBar(notification: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row{
            Icon(painterResource(R.drawable.chat_bubble), contentDescription = null, tint = Color.Black)
            Column{
                Text("Notification", color= Color.Gray)
                Row{
                    Text("Send notification: ", fontSize = 14.sp)
                    Text(notification, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Icon(painterResource(R.drawable.settings), contentDescription = null, tint = Color.Gray, modifier = Modifier.size(46.dp))
    }
}