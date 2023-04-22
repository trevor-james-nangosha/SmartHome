package com.nangosha.smarthome.routines

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nangosha.smarthome.R

@Composable
fun RoutineItem(name: String, lastRun: String, showLastRun: Boolean = true){
    Column{
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 15.dp),
        ) {
            Icon(
                painterResource(R.drawable.schedule),
                contentDescription = null,
                tint = Color.Black
            )
            if (showLastRun) {
                Column(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(name, color = Color.Black, fontSize = 20.sp)
                    Text("Last Run: $lastRun", color = Color.Gray, fontSize = 13.sp)
                }
            } else {
                Column(
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(name, color = Color.Black, fontSize = 20.sp)
                }
            }
        }

        Divider (color = Color.Gray, modifier = Modifier.height(1.dp).fillMaxWidth())
    }
}
