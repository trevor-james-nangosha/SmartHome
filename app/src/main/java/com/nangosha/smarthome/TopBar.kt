package com.nangosha.smarthome

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nangosha.smarthome.ui.theme.CustomYellow

@Composable
fun TopBar(title: String = stringResource(R.string.app_name), back: Boolean = false, actions: Boolean = false){
    TopAppBar(
        title = { Text(title, color = Color.White) },
        backgroundColor  = CustomYellow,
        navigationIcon = {
            if(back){
                // todo
                // how do you access the page you have been at so that you can go back to it
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                }
            }

        },
        actions = {
            if(actions){
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = null, tint = Color.White)
                }
            }
        }
    )

}

@Composable
fun RoutinesTopBar(){
    TopAppBar(
        title = {},
        backgroundColor = CustomYellow,
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Menu, contentDescription = null, tint = Color.White)
            }
        },
    )
}

@Composable
fun HomeTopBar(){
    TopAppBar(
        title = {Text("My Smart Home",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )},
        backgroundColor = CustomYellow,
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(painterResource(R.drawable.edit), contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.White)
            }
        },
    )
}

@Composable
fun IdeasScreenTopBar(){
    TopAppBar (
        content = {Text("My Smart Home",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )},
        backgroundColor = CustomYellow,
        contentColor = Color.White
    )
}

@Composable
fun CreateRoutineTopBar(){
    TopAppBar(
        backgroundColor = CustomYellow,
        title = { Text(text = "Create a Routine", color = Color.White)},
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Default.Close, contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Default.Check, contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.White)
            }
        },
    )
}