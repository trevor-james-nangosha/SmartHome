package com.nangosha.smarthome.routines

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nangosha.smarthome.*
import com.nangosha.smarthome.services.AlarmReceiver
import com.nangosha.smarthome.ui.theme.CustomGrey
import com.nangosha.smarthome.ui.theme.CustomYellow
import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.*

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateRoutines (navController: NavController, viewModel: SmartHomeViewModel, context: Context){
    val randomPlaceholderText = "Want this routine to run automatically? Add an event below"

    val uiState by viewModel.uiState.collectAsState()
    val routineName = uiState.routineName
    val routineTime = uiState.routineTime
    val routineId = uiState.routineId
    val notificationText = uiState.notificationText
    val showTimePicker = uiState.showTimePicker
    // val lastRun = uiState.lastRun
    val showNotificationModal = uiState.showEnterNotificationModal
    val showCreateRoutineLinearLoader = uiState.showCreateRoutineLinearLoader

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = {
            Column {
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)){

                    TextField(value = routineName ,
                        textStyle = TextStyle(fontSize = 20.sp),
                        colors = TextFieldDefaults.textFieldColors(
                            trailingIconColor = CustomYellow,
                            backgroundColor = Color.White,
                            focusedIndicatorColor = CustomYellow,
                            cursorColor = CustomYellow,
                            textColor = Color.Gray,
                            placeholderColor = Color.Gray
                        ) ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp)
                            .padding(vertical = 10.dp)
                            .background(Color.White),
                        singleLine = true,
                        placeholder = { Text(text = "Routine Name", fontSize = 20.sp) },
                        onValueChange = { newText ->
                                viewModel.updateRoutineName(newText)
                        })
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)) {
                        Text("When", fontSize = 20.sp)
                }
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(CustomGrey)
                        .padding(vertical = 15.dp, horizontal = 10.dp)
                        .fillMaxWidth())
                {
                    if(routineTime.isEmpty()){
                        Text(randomPlaceholderText, fontSize = 20.sp)
                    } else if(routineTime.isNotEmpty()){
                        // todo
                        // this should be an array of events, not just one.
                        EventBar(time = routineTime)
                    }


                    if (showTimePicker){
                        // todo
                        // add a clock icon and settings icon to this thing.
                        timePicker(viewModel)
                        viewModel.updateShowTimePicker(false)
                    }

                    if (showNotificationModal) {
                        EnterNotificationDialog(
                            onDismiss = {
                                viewModel.updateShowEnterNotificationModal(false)
                                coroutineScope.launch {
                                    viewModel.updateShowCreateRoutineLinearLoader(false)
                                }
                            },
                            onConfirm = { notification ->
                                viewModel.updateNotificationText(notification)
                                viewModel.updateShowEnterNotificationModal(false)
                                coroutineScope.launch {
                                    if(routineName.isNotBlank()){ // you cannot have a routine without a name
                                        viewModel.updateShowCreateRoutineLinearLoader(true)
                                    }
                                }
                            })
                    }

                    if (showCreateRoutineLinearLoader){
                        showLinearLoader(showCreateRoutineLinearLoader,
                            coroutineScope,
                            navController,
                            viewModel,
                            name = routineName,
                            scheduledFor = routineTime,
                            notification = notificationText,
                            context,
                            routineId
                        )
//                        scheduleNotification(routineName, notificationText, context, routineTime, viewModel)
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp)
                        .padding(vertical = 10.dp)
                ) {
                    Column {
                        Text("Add Event", fontSize = 20.sp, modifier = Modifier.padding(end = 15.dp, top = 10.dp))
                    }
                    Button(onClick = { navController.navigate(Screen.CreateEvent.route) },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan, contentColor = Color.White
                        )
                    ){
                        Icon(
                            Icons.Default.Add, contentDescription = "Create a new Routine", tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 0.dp, vertical = 0.dp)
                                .size(25.dp)
                        )
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)) {
                    Text("Run these actions",  fontSize = 20.sp)
                }

                if(notificationText.isNotEmpty()){
                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(CustomGrey)
                            .padding(vertical = 15.dp, horizontal = 10.dp)
                            .fillMaxWidth())
                    {
                        NewNotificationBar(notification = notificationText)
                    }
                } else if (notificationText.isEmpty()){
                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(CustomGrey)
                            .padding(vertical = 15.dp, horizontal = 10.dp)
                            .fillMaxWidth())
                    {
                        Text("No actions. Tap below to add one.", fontSize = 20.sp)
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp)
                        .padding(vertical = 10.dp)
                ) {
                    Column {
                        Text("Add Action", fontSize = 20.sp, modifier = Modifier.padding(end = 15.dp, top = 10.dp))
                    }
                    Button(onClick = { navController.navigate(Screen.CreateAction.route) },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan, contentColor = Color.White
                        )
                    ){
                        Icon(
                            Icons.Default.Add, contentDescription = "Create a new Routine", tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 0.dp, vertical = 0.dp)
                                .size(25.dp)
                        )
                    }
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)) {
                    Text("But only if",  fontSize = 20.sp)
                }

                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(CustomGrey)
                        .padding(vertical = 15.dp, horizontal = 10.dp)
                        .fillMaxWidth())
                {
                    Text("Add an event before adding conditions.", fontSize = 20.sp)
                }

                if(routineTime.isNotEmpty()){
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                            .padding(vertical = 10.dp)
                    ) {
                        Column {
                            Text("Add Condition", fontSize = 20.sp, modifier = Modifier.padding(end = 15.dp, top = 10.dp))
                        }
                        Button(onClick = { /* do something here*/ },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier.size(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan, contentColor = Color.White
                            )
                        ){
                            Icon(
                                Icons.Default.Add, contentDescription = "Create a new condition", tint = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 0.dp, vertical = 0.dp)
                                    .size(25.dp)
                            )
                        }
                    }
                }

                if (showTimePicker){
                    // todo
                    // when this row appears, then it must appear from now on
                    // right now, when i visit another screen and somehow showTimePicker is evaluated to false
                    // work on this
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                            .padding(vertical = 10.dp)
                    ) {
                        Column(){
                            Text("Add Condition", fontSize = 20.sp, modifier = Modifier.padding(end = 15.dp, top = 10.dp))
                        }
                        Button(onClick = { navController.navigate(Screen.CreateEvent.route) },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier.size(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan, contentColor = Color.White
                            )
                        ){
                            Icon(
                                Icons.Default.Add, contentDescription = "Create a new Routine", tint = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 0.dp, vertical = 0.dp)
                                    .size(25.dp)
                            )
                        }
                    }
                }
            }
        },
        topBar = { CreateRoutineTopBar()},

        )
}


@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("CoroutineCreationDuringComposition", "ComposableNaming")
@Composable
fun showLinearLoader(showLinearLoader: Boolean,
                     coroutineScope: CoroutineScope,
                     navController: NavController,
                     viewModel: SmartHomeViewModel,
                     name: String,
                     scheduledFor: String = "",
                     notification: String = "",
                     ctx: Context,
                     routineId: Int
            ){
    if(showLinearLoader){
        CreateRoutineLinearLoader()

        coroutineScope.launch {
            try {
                withTimeout(3000) {
                    delay(2000)
                    saveRoutine(viewModel, name, scheduledFor, notification, routineId)
                    scheduleNotification(name, notification, ctx, scheduledFor, routineId)
                    viewModel.updateShowCreateRoutineLinearLoader(false)
                    withContext(Dispatchers.Main) {
                        navController.navigate(Screen.Routines.route)
                    }
                }
            } catch (e: TimeoutCancellationException) {
                // do nothing
            }
        }
    }
}

fun saveRoutine(viewModel: SmartHomeViewModel, name: String, scheduledFor: String = "", notification: String = "", id: Int){
    if (scheduledFor.isBlank()){
        if(notification.isBlank()){
            viewModel.updateRoutines(Routine(name, "Never", notification = "NO_NOTIFICATION", routineID = id))
        }
        viewModel.updateRoutines(Routine(name,"Never", notification = notification, routineID = id))
    }

    viewModel.updateRoutines(Routine(name, scheduledFor, notification = notification, routineID = id))
    viewModel.updateRoutineId()
    viewModel.resetState()
}

@RequiresApi(Build.VERSION_CODES.M)
fun scheduleNotification(routineName: String, content: String, ctx: Context, routineTime: String, notificationId: Int){
    val duration = getNotificationDuration(routineTime)
    val alarmMgr: AlarmManager?

    alarmMgr = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val alarmIntent: PendingIntent = Intent(ctx, AlarmReceiver::class.java).let { intent ->
        intent.putExtra("routine_name", routineName)
        intent.putExtra("content", content)
        intent.putExtra("id", notificationId)

        PendingIntent.getBroadcast(ctx, 0, intent, PendingIntent.FLAG_MUTABLE)
    }

    alarmMgr.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        duration,
        alarmIntent
    )
//    viewModel.resetState()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getNotificationDuration(time: String): Long {
    val currentTime = LocalTime.now()
    val targetTime = LocalTime.parse(time)
    val hours = currentTime.until(targetTime, ChronoUnit.HOURS)
    val minutes = currentTime.until(targetTime, ChronoUnit.MINUTES) % 60
    var duration = 0L

    if (hours != 0L && minutes != 0L){
        duration = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000)
    } else if(hours == 0L && minutes != 0L){
        duration =  minutes * 60 * 1000
    } else if(hours != 0L){
        duration = hours * 60 * 60 * 1000
    }

    // todo, when someone selects the current date, the duration does not work so well.

    return System.currentTimeMillis() + duration
}
