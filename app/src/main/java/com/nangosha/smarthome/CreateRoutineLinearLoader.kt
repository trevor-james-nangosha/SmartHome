package com.nangosha.smarthome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.nangosha.smarthome.ui.theme.CustomYellow

@Composable
fun CreateRoutineLinearLoader(){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AlertDialog(onDismissRequest = { /*onDismiss*/ },
            text = {
                LinearProgressIndicator(color = CustomYellow, modifier = Modifier.fillMaxWidth())
                Text("Creating new routine.",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center)
            },
            confirmButton = {},
            dismissButton = {})


    }
}