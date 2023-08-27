package com.example.creditcalculator.domain

import androidx.compose.runtime.MutableState
import java.math.RoundingMode

class CalculateAnnuityPaymentUseCase {
    fun execute(sum: MutableState<String>, perc: MutableState<String>, month: MutableState<String>): MutableList<String>{
        val i = (perc.value.toDouble()/100)/12
        val Sk = sum.value.toDouble()
        val n = month.value.toDouble()
        var result: Double? = null
        var resultString: String? = null
        var roundedResult: Float? =null
        val resultList = mutableListOf("")
        val an=(n-1).toInt()

        for(a in 0..an){
            result = Sk*(i+(i/(Math.pow(1+i,n)-1)))
            roundedResult=result.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toFloat()
            resultString=roundedResult.toString()
            if(a==0)
                resultList.set(0, resultString)
            else
                resultList.add(a, resultString)
        }
        return resultList
    }
}