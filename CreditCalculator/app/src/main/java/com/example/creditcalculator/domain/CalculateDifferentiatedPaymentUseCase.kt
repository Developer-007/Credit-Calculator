package com.example.creditcalculator.domain

import androidx.compose.runtime.MutableState
import java.math.RoundingMode

class CalculateDifferentiatedPaymentUseCase {
    fun execute(sum: MutableState<String>, perc: MutableState<String>, month: MutableState<String>): MutableList<String>{
        val S=sum.value.toFloat()
        val N=month.value.toFloat()
        val i=(perc.value.toFloat()/100)/12
        var Sn=sum.value.toFloat()
        val b = S/N
        var result: Float? = null
        var resultString: String? = null
        var roundedResult: Float? =null
        val resultList = mutableListOf("")
        val an=(N-1).toInt()

        for(a in 0..an){
            if(a==0){
                result=b+(Sn*i)
                roundedResult=result.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toFloat()
                resultString=roundedResult.toString()
                resultList.set(0,resultString)
            } else{
                Sn=Sn-b
                result=b+(Sn*i)
                roundedResult=result.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toFloat()
                resultString=roundedResult.toString()
                resultList.add(a,resultString)
            }
        }

        return resultList
    }
}