package com.nangosha.smarthome.main_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nangosha.smarthome.BottomBar
import com.nangosha.smarthome.R
import com.nangosha.smarthome.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ThingsScreen (navController: NavController){
    Scaffold(
        content = {
            Column{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.things_bar_icon),
                        contentDescription = "Astronaut holding a pencil",
                        tint = Color.Gray,
                        modifier = Modifier.size(180.dp)
                    )

                    Text(text = "No things!", color = Color.Gray, fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )

                    Text(
                        text = "It looks like we did not discover any devices.",
                        color = Color.Gray,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 5.dp),
                    )

                    Text(text = "Try an option below.", color = Color.Gray, fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 5.dp),
                    )
                }

                    Divider (color = Color.Gray, modifier = Modifier.height(1.dp).padding(horizontal = 15.dp))

                    ThingsItemBar(text = "Run discovery")
                    ThingsItemBar(text = "Add a cloud account")
                    ThingsItemBar(text = "View our supported devices")
                    ThingsItemBar(text = "Contact support")
                }
       },
        topBar = { TopBar() },
        bottomBar = { BottomBar(navController) }
    )

}

@Composable
fun ThingsItemBar(text: String, icon: Int? = null){
    Column {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp)){
            Button(onClick = {/* do something on click*/},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.size(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan, contentColor = Color.White)
            ) {
                if(icon !== null){
                    Icon(
                        painterResource(icon),
                        contentDescription = "Thing!!!!!!", tint = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 0.dp, vertical = 0.dp)
                            .size(25.dp)
                    )
                } else{
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Thing!!!!!!", tint = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 0.dp, vertical = 0.dp)
                            .size(25.dp)
                    )
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ){
                Text(text,  fontSize = 20.sp, color = Color.Cyan)
            }
        }
    }
}