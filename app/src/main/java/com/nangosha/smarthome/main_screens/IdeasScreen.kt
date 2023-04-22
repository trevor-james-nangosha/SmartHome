package com.nangosha.smarthome

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nangosha.smarthome.ui.theme.CustomBackground

//import java.lang.reflect.Modifier

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IdeasScreen(navController: NavController) {

    Scaffold(
        content = { Column(modifier = Modifier.background(CustomBackground).fillMaxSize()) {

            Text(text = "More recommendations",
                fontSize = 25.sp, color = Color.DarkGray,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            )
            Text(text = "Even more recommendations!",
                fontSize = 15.sp, color = Color.DarkGray,
                modifier = Modifier.padding(start = 20.dp, top = 0.dp)
            )

            Card(
                elevation = 4.dp,
                // backgroundColor = Color.Yellow,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.height(200.dp).width(700.dp).padding(10.dp)
            ){
                Image(
                    painter = painterResource(R.drawable.umbrella_icon),
                    contentDescription = "Sunflower in full blossom",
                    alpha = 0.8F,
                    alignment = Alignment.TopStart,
                    contentScale = ContentScale.FillBounds,
                )
                Column {
                    Text(text = "Thanksgiving dinner ready",
                        fontSize = 25.sp, color =Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 20.dp , top = 20.dp))
                    Text(text = "Send me a notification!!!!!!",
                        fontSize = 15.sp, color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp))
                }

            }
        } },
        topBar = { IdeasScreenTopBar() },
        bottomBar = { BottomBar(navController) }
    )


}