package com.nangosha.smarthome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SmartHomeViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(AppUIState())
    var uiState: StateFlow<AppUIState> = _uiState.asStateFlow()

    fun updateRoutines(routine: Routine){
        _uiState.update { currentState ->
            val updatedRoutines = currentState.routines.toMutableList().apply {
                add(routine)
            }
            currentState.copy(routines = updatedRoutines)
        }
    }

    fun updateRoutineTime(time: String){
        _uiState.update { currentState ->
            currentState.copy(routineTime = time)
        }
    }

    fun updateNotificationText(notification: String){
        _uiState.update { currentState ->
            currentState.copy(notificationText = notification)
        }
    }

    fun updateRoutineName(name: String){
        _uiState.update { currentState ->
            currentState.copy(routineName = name)
        }
    }

    fun updateShowEnterNotificationModal(value: Boolean){
        _uiState.update { currentState ->
            currentState.copy(showEnterNotificationModal = value)
        }
    }

    fun updateShowCreateRoutineLinearLoader(value: Boolean){
        _uiState.update { currentState ->
            currentState.copy(showCreateRoutineLinearLoader = value)
        }
    }

    fun updateShowTimePicker(value: Boolean){
        _uiState.update { currentState ->
            currentState.copy(showTimePicker = value)
        }
    }

    fun resetState(){
        _uiState.update { currentState ->
            currentState.copy(routineName = "", routineTime = "", notificationText = "")
        }
    }

}