package com.nangosha.smarthome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nangosha.smarthome.ui.theme.CustomYellow

@Composable
fun EnterNotificationDialog(onDismiss: () -> Unit, onConfirm:  (name: String) -> Unit){
    val name = remember { mutableStateOf(TextFieldValue("")) }
    AlertDialog(
        onDismissRequest = onDismiss, title = {
                Text(
                    text = "Enter a value",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
    },  text = {
        Column {
            TextField(
                value = name.value,
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = { name.value = it },
                singleLine = true,
                placeholder = { Text(text = "Type notification....", fontSize = 18.sp) },
                colors = TextFieldDefaults.textFieldColors(
                    trailingIconColor = CustomYellow,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = CustomYellow,
                    cursorColor = CustomYellow,
                    textColor = Color.Gray,
                    placeholderColor = Color.Gray
                ) ,
            )
        }
    },
        confirmButton = {
            Text(text = "OK",   color = CustomYellow,
                fontSize = 20.sp,
                modifier = Modifier.clickable(true, onClick = {
                    val input = name.value.text
                    if (input.isNotEmpty()) onConfirm.invoke(input)
                }).padding(end = 20.dp, bottom = 20.dp, start = 20.dp)
            )
        },
        dismissButton = {Text(text = "CANCEL",  color = CustomYellow, fontSize = 20.sp,
            modifier = Modifier.clickable(true, onClick = onDismiss).padding(bottom = 20.dp)
            )
        }

    )
}