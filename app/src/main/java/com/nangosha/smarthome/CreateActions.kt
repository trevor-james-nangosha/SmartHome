package com.nangosha.smarthome

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateActions(navController: NavController, viewModel: SmartHomeViewModel){
    Scaffold (
        content = {
                  TabLayout(navController, viewModel)
        },
        topBar = { TopBar(title = "Select a thing", back = true, actions = true) }
    )

}