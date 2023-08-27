package com.example.creditcalculator.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MyAlertDialog(dialogState: MutableState<Boolean>, text: String){
    androidx.compose.material3.AlertDialog(onDismissRequest = {
        dialogState.value=false
    },
        confirmButton = {
            TextButton(onClick = {
                dialogState.value=false
            }) {
                Text("OK")
            }
        },
        title = {
            Column(modifier= Modifier.fillMaxWidth()){
                Text(text = "Ошибка!", fontSize=25.sp)
                Text(text = text, fontSize=15.sp)
            }
        })
}