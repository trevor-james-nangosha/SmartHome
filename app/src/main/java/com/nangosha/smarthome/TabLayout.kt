package com.nangosha.smarthome

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.nangosha.smarthome.ui.theme.CustomGrey
import kotlinx.coroutines.launch

@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun TabLayout(navController: NavController, viewModel: SmartHomeViewModel){
    val pagerState = rememberPagerState(pageCount = 3)

    Column {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, navController = navController, viewModel)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "THINGS",
        "SCENES",
        "ROUTINES"
    )

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = Color.Black,
        backgroundColor = CustomGrey,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.Black
            )
        }
    ) {
        list.forEach { title ->
            Tab(
                text = {
                    Text(title,
                        color = if (pagerState.currentPage == list.indexOf(title)) Color.Black else Color.Gray
                    )
                },
                selected = pagerState.currentPage == list.indexOf(title),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(list.indexOf(title))
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, navController: NavController, viewModel: SmartHomeViewModel) {
    HorizontalPager(state = pagerState) {
            page ->
        when (page) { // this is like a switch statement. People and syntactic sugar!!!!!!!
            0 -> TabContentScreenThings (navController, viewModel)
            1 -> TabContentScreenScenes()
            2 -> TabContentScreenRoutines()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabContentScreenThings(navController: NavController, viewModel: SmartHomeViewModel) {
    val showNotificationBar = remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.fillMaxSize(),
        // horizontalAlignment = Alignment.CenterHorizontally,
        // verticalArrangement = Arrangement.Center
    ) {

        Row (
            modifier = Modifier.fillMaxWidth().clickable(enabled = true,
                    onClick = {
                        showNotificationBar.value =
                            toggleShowNotificationBar(showNotificationBar.value)
                    })
        ){
            if (showNotificationBar.value) {
                Column(
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 10.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.chat_bubble),
                        tint = Color.Black,
                        contentDescription = "Notifications",
                        modifier = Modifier
                            .size(80.dp)
                    )
                    Text("Notification", fontSize = 20.sp)
                }
            } else {
                Text(
                    "Enter your notification text here.",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        viewModel.updateShowEnterNotificationModal(true)
                        navController.navigate(Screen.CreateRoutine.route)
                    }).fillMaxWidth().padding(start= 10.dp, top = 5.dp)
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabContentScreenScenes() {
    Column(
        modifier = Modifier.fillMaxSize(),
        // horizontalAlignment = Alignment.CenterHorizontally,
        // verticalArrangement = Arrangement.Center
    ) {
        Text("Nothing yet........")
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabContentScreenRoutines() {
    Column(
        modifier = Modifier.fillMaxSize(),
        // horizontalAlignment = Alignment.CenterHorizontally,
        // verticalArrangement = Arrangement.Center
    ) {
        Text("Nothing yet........")
    }
}


fun toggleShowNotificationBar(value: Boolean): Boolean {
    return !value
}