package com.example.creditcalculator.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.creditcalculator.domain.CalculateAnnuityPaymentUseCase
import com.example.creditcalculator.domain.CalculateDifferentiatedPaymentUseCase
import com.example.creditcalculator.ui.theme.Cyan1

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainScreen(){
    val calculateAnnuityPaymentUseCase = CalculateAnnuityPaymentUseCase()
    val calculateDifferentiatedPaymentUseCase = CalculateDifferentiatedPaymentUseCase()
    var isExpanded by  remember{
        mutableStateOf(false)
    }
    var payType by remember{
        mutableStateOf("Аннуитетный")
    }
    val sum = remember{
        mutableStateOf("")
    }
    val perc = remember{
        mutableStateOf("")
    }
    val months = remember{
        mutableStateOf("")
    }
    val credSumList = remember{
        mutableStateListOf<String>()
    }

    val dialogState = remember {
        mutableStateOf(false)
    }
    val executeState = remember {
        mutableStateOf(false)
    }
    if(dialogState.value){
        MyAlertDialog(dialogState = dialogState, text = "Заполните все поля")
    }
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Box(){
            Column(){
                Card(
                    modifier= Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Cyan1
                    )
                ){
                    Column(modifier= Modifier.padding(7.dp)){
                        Text(text="Тип платежа:", color = Color.White)
                        ExposedDropdownMenuBox(expanded = isExpanded,
                            onExpandedChange = {isExpanded = it}) {
                            TextField(
                                value = payType,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    containerColor = Color.White,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                                DropdownMenuItem(
                                    text = {
                                        Text(text = "Аннуитетный")
                                    },
                                    onClick = {
                                        payType = "Аннуитетный"
                                        isExpanded = false
                                    })
                                DropdownMenuItem(
                                    text = {
                                        Text(text = "Дифференцированный")
                                    },
                                    onClick = {
                                        payType = "Дифференцированный"
                                        isExpanded = false
                                    })
                            }
                        }
                        Row(
                            modifier= Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text="Сумма:", color = Color.White, modifier=Modifier.fillMaxWidth(0.35f))
                            TextField(
                                value = sum.value,
                                onValueChange = {
                                    sum.value = it
                                },
                                keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Number),
                                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                                shape = RoundedCornerShape(10.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.White,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                )
                            )
                        }
                        Row(
                            modifier= Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text="Годовая ставка, %:", color = Color.White, modifier=Modifier.fillMaxWidth(0.35f))
                            TextField(
                                value = perc.value,
                                onValueChange = {
                                    perc.value = it
                                },
                                keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Number),
                                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                                shape = RoundedCornerShape(10.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.White,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                )
                            )
                        }
                        Row(
                            modifier= Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text="Кол-во месяцев:", color = Color.White, modifier=Modifier.fillMaxWidth(0.35f))
                            TextField(
                                value = months.value,
                                onValueChange = {
                                    months.value = it
                                },
                                keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Number),
                                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                                shape = RoundedCornerShape(10.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.White,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                )
                            )
                        }
                    }
                }
                LazyColumn(modifier=Modifier.fillMaxWidth().fillMaxHeight(0.9f)){
                    itemsIndexed(
                        credSumList
                    ){index, item ->
                        ListItem(payment = "Месяц ${index+1}-й:", creditSum = item)
                    }
                }
            }
        }
        Button(onClick = {
                         if(sum.value=="" || perc.value=="" || months.value==""){
                             dialogState.value=true
                         } else{
                             if(payType=="Аннуитетный"){
                                 credSumList.clear()
                                 credSumList.addAll(calculateAnnuityPaymentUseCase.execute(sum, perc, months))
                             }
                             else if(payType=="Дифференцированный"){
                                 credSumList.clear()
                                 credSumList.addAll(calculateDifferentiatedPaymentUseCase.execute(sum, perc, months))
                             }
                         }
        },
            modifier= Modifier
                .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor=Cyan1
            )
        ) {
            Text("Рассчитать")
        }
    }
}