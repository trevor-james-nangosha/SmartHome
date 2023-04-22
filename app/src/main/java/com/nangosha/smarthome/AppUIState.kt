package com.nangosha.smarthome

data class AppUIState(
    // todo
    // There is an issue, these fields do not belong to the state as a whole
    // they belong to a particular routine. This is a poor abstraction!!!!!!!
    // ------------------------------------------------------
    var routineName: String = "",
    var routineTime: String  = "",
    val notificationText: String = "",
    val lastRun: String = "Never",
    // ------------------------------------------------------

    val showCreateRoutineLinearLoader: Boolean = false,
    val showEnterNotificationModal: Boolean = false,
    val showTimePicker: Boolean = false,
    val routines: MutableList<Routine> = mutableListOf(),
)

data class Routine(
    val name: String,
    val scheduledFor : String,
    val notification: String,
    val lastRun: String = "Never",

    )