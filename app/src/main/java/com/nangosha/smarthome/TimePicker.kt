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
        mContext,
        { _, mHourr: Int, mMinutee: Int ->
            var newTime = ""
            var hours = ""
            var minutes = ""
            hours = if (mHourr < 10){
                if (mHourr == 0){
                    "00"
                } else {
                    mHourr.toString().padStart(2, '0')
                }
            }else{
                mHourr.toString()
            }

            minutes = if (mMinutee < 10){
                if (mMinutee == 0){
                    "00"
                } else{
                    mMinutee.toString().padStart(2, '0')
                }
            } else{
                mMinutee.toString()
            }
            newTime = "$hours:$minutes"
            viewModel.updateRoutineTime(newTime)
        }, mHour, mMinute, true
    )

    mTimePickerDialog.show()
}