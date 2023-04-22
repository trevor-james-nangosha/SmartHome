package com.nangosha.smarthome

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.*

@SuppressLint("ComposableNaming")
@Composable
fun timePicker(viewModel: SmartHomeViewModel) {
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    val mTimePickerDialog = TimePickerDialog(
        // todo
        // apply the custom yellow style to the face of the dialog

        mContext,
        { _, mHourr: Int, mMinutee: Int ->
            val newTime = "$mHourr:$mMinutee"
            viewModel.updateRoutineTime(newTime)
        }, mHour, mMinute, true
    )

    mTimePickerDialog.show()
}