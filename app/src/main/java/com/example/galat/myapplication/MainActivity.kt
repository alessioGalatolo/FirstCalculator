package com.example.galat.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import kotlin.math.*


class MainActivity : AppCompatActivity() {
    private var approximationNumber = 2
    var memoryVar = 0.0
    var numberWithColon : String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeNumberButtons()
        initializeOpButtons()
        initializeAdvButtons()
    }

    private fun Double.roundToDecimalPlaces(approx: Int) = BigDecimal(this).setScale(approx, BigDecimal.ROUND_HALF_UP).toDouble()

    private fun initializeNumberButtons(){
        fun addNumber(number: Int) {
            if(output_sign.text == "=")
                output_sign.text = ""
            if(outputDot.visibility == View.VISIBLE){
                numberWithColon = output1.text.toString() + "." + number.toString()
                if(number != 0)
                    output1.text = (output1.text.toString().toDouble() + number.toDouble() * 10.0.pow(2 - (output1.text.toString().toDouble() % 1).toString().length)).toString()
                else {
                    output1.text = output1.text.toString().toDouble().roundToDecimalPlaces(1).toString()
                }
                outputDot.visibility = View.INVISIBLE
                approximationNumber = 1
            }else if (numberWithColon != "0"){
                approximationNumber++
                numberWithColon += number.toString()
                if (number != 0)
//                    output1.text = (output1.text.toString().toDouble() + number.toDouble() * 10.0.pow(1 - (output1.text.toString().toBigDecimal() % 1.toBigDecimal()).toString().length)).roundToDecimalPlaces(approximationNumber).toString()
                    output1.text = numberWithColon
                else {
                    output1.text = numberWithColon
                }
                outputDot.visibility = View.INVISIBLE
            }else{
                if (output1.text.toString().toDouble() == 0.0) {
                    output1.text = number.toString()
                } else {
                    output1.text = (output1.text.toString().toDouble() * 10.0 + number).toString()
                }
            }
        }

        b_0.setOnClickListener {
            addNumber(0)
        }
        b_1.setOnClickListener {
            addNumber(1)
        }
        b_2.setOnClickListener {
            addNumber(2)
        }
        b_3.setOnClickListener {
            addNumber(3)
        }
        b_4.setOnClickListener {
            addNumber(4)
        }
        b_5.setOnClickListener {
            addNumber(5)
        }
        b_6.setOnClickListener {
            addNumber(6)
        }
        b_7.setOnClickListener {
            addNumber(7)
        }
        b_8.setOnClickListener {
            addNumber(8)
        }
        b_9.setOnClickListener {
            addNumber(9)
        }
    }

    private fun initializeOpButtons(){
        fun addSymbol(symbol: String) {
            when (output_sign.text) {
                "+" -> output2.text = (output2.text.toString().toDouble() + output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
                "-" -> output2.text = (output2.text.toString().toDouble() - output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
                "*" -> output2.text = (output2.text.toString().toDouble() * output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
                "/" -> if(output1.text.toString().toDouble() != 0.0) {
                    output2.text = (output2.text.toString().toDouble() / output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
                }else{
                    Toast.makeText(this,"Cannot divide by zero", Toast.LENGTH_SHORT).show()
                }
                "^" -> output2.text = (output2.text.toString().toDouble().pow(output1.text.toString().toDouble())).roundToDecimalPlaces(approximationNumber).toString()
                "=" -> approximationNumber = 2
                else -> output2.text = output1.text
            }
            output_sign.text = symbol
            output1.text = "0"
            if(numberWithColon != "0"){
                output2.text = numberWithColon
                numberWithColon = "0"
            }
            if(output2.text.toString().toDouble() % 1 == 0.0)
                output2.text = floor(output2.text.toString().toDouble()).roundToInt().toString()
        }
        b_plus.setOnClickListener {
            addSymbol("+")
        }
        b_minus.setOnClickListener {
            addSymbol("-")
        }
        b_molt.setOnClickListener {
            addSymbol("*")
        }
        b_div.setOnClickListener {
            addSymbol("/")
        }
        b_equal.setOnClickListener {
            addSymbol("=")
        }
        b_sqpow.setOnClickListener {
            if(output1.text.toString().toDouble() * 2 == 0.0 && output2.text != "")
                output2.text = output2.text.toString().toDouble().pow(2).roundToDecimalPlaces(approximationNumber).toString()
            else
                output2.text = output1.text.toString().toDouble().pow(2).roundToDecimalPlaces(approximationNumber).toString()
            output_sign.text = "="
            output1.text = "0"
            if(output2.text.toString().toDouble() % 1 == 0.0)
                output2.text = floor(output2.text.toString().toDouble()).roundToInt().toString()
        }
        b_sqrt.setOnClickListener {
            if(output1.text.toString().toDouble() * 2 == 0.0 && output2.text != "") {
                output2.text = sqrt(output2.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            }else
                output2.text = sqrt(output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            output_sign.text = "="
            output1.text = "0"
            if(output2.text.toString().toDouble() % 1 == 0.0)
                output2.text = floor(output2.text.toString().toDouble()).roundToInt().toString()
        }
        b_dot.setOnClickListener {
            if(output1.text.toString().toBigDecimal() % 1.toBigDecimal() == 0.toBigDecimal())
                if(outputDot.visibility == View.VISIBLE)
                    outputDot.visibility = View.INVISIBLE
                else
                    outputDot.visibility = View.VISIBLE
        }
    }

    fun landPowerButton(view: View){
        when (output_sign.text) {
            "+" -> output2.text = (output2.text.toString().toDouble() + output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            "-" -> output2.text = (output2.text.toString().toDouble() - output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            "*" -> output2.text = (output2.text.toString().toDouble() * output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            "/" -> if(output1.text.toString().toDouble() != 0.0) {
                output2.text = (output2.text.toString().toDouble() / output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            }else{
                Toast.makeText(this,"Cannot divide by zero", Toast.LENGTH_SHORT).show()
            }
            "^" -> output2.text = (output2.text.toString().toDouble().pow(output1.text.toString().toDouble())).roundToDecimalPlaces(approximationNumber).toString()
            "=" -> approximationNumber = 2
            else -> output2.text = output1.text
        }
        output_sign.text = "^"
        output1.text = "0"
        if(numberWithColon != "0"){
            output2.text = numberWithColon
            numberWithColon = "0"
        }
        if(output2.text.toString().toDouble() % 1 == 0.0)
            output2.text = floor(output2.text.toString().toDouble()).roundToInt().toString()
    }

    private fun initializeAdvButtons(){
        b_canc.setOnClickListener {
            if(output1.text.toString() != "0" && output1.text.toString().length > 1) {
                output1.text = output1.text.toString().subSequence(0, output1.text.toString().length - 1)
                if (output1.text.toString()[output1.text.toString().length - 1] == '.')
                    output1.text = output1.text.toString().subSequence(0, output1.text.toString().length - 1)
                if (numberWithColon != "0"){
                    numberWithColon = numberWithColon.subSequence(0, numberWithColon.length - 1).toString()
                    if (numberWithColon[numberWithColon.length - 1] == '.')
                        numberWithColon = numberWithColon.subSequence(0, numberWithColon.length - 1).toString()
                }
            }
            else
                cancAll()
        }
        b_canc.setOnLongClickListener {
            cancAll()
            return@setOnLongClickListener true
        }

    }

    private fun cancAll(){
        output1.text = "0"
        output2.text = ""
        output_sign.text = ""
        approximationNumber = 2
    }

    fun landButtons(symbol: String){
        if (output1.text.toString().toDouble() * 10.0 != 0.0) {
            output2.text = output1.text
            output1.text = "0"
            output_sign.text = "="
        }else if(output2.text != ""){
            output_sign.text = "="
        }else{
            Toast.makeText(this, "Please write some numbers before using this function", Toast.LENGTH_SHORT).show()
            output2.text = 0.toString()
        }
        when(symbol){
            "log" -> if (output2.text.toString().toDouble() > 0)output2.text = log10(output2.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            else Toast.makeText(this, "Math error", Toast.LENGTH_SHORT).show()
            "tan" -> output2.text = tan(output2.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            "sin" -> output2.text = sin(output2.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            "cos" -> output2.text = cos(output2.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber).toString()
            "ln"  -> if (output2.text.toString().toDouble() > 0)output2.text = log(output2.text.toString().toDouble(), E).roundToDecimalPlaces(approximationNumber).toString()
            else Toast.makeText(this, "Math error", Toast.LENGTH_SHORT).show()
        }
        if(output2.text.toString().toDouble() % 1 == 0.0)
            output2.text = floor(output2.text.toString().toDouble()).roundToInt().toString()
    }

    fun landLnButton(view: View) = landButtons("ln")
    fun landTanButton(view: View) = landButtons("tan")
    fun landSinButton(view: View) = landButtons("sin")
    fun landCosButton(view: View) = landButtons("cos")
    fun landLogButton(view: View) = landButtons("log")
    fun landPerCent(view: View){
        if(output2.text == "")
            Toast.makeText(this, "Error in using % function", Toast.LENGTH_SHORT).show()
        else
            output1.text = (output2.text.toString().toDouble() * output1.text.toString().toDouble() / 100.0).toString()
    }

    fun mPlusButton(view: View){
        if( output1.text.toString().toDouble() != 0.0){
            memoryVar = (memoryVar + output1.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber)
            output1.text = "0"
            output_sign.text = "="
        }else if(output2.text.toString().toDouble() != 0.0){
            memoryVar = (memoryVar + output2.text.toString().toDouble()).roundToDecimalPlaces(approximationNumber)
            output1.text = "0"
            output_sign.text = "="
        }else{
            Toast.makeText(this, "Cannot add 0", Toast.LENGTH_SHORT).show()
        }
    }

    fun rmButton(view: View){
        if( memoryVar != 0.0) {
            output1.text = memoryVar.toString()
            if(output_sign.text == "=")
                output_sign.text = ""
        }
        else
            Toast.makeText(this, "Memory is empty", Toast.LENGTH_SHORT).show()

    }

    fun minButton(view: View){
        if(output1.text.toString().toDouble() != 0.0) {
            memoryVar = output1.text.toString().toDouble()
            output1.text = "0"
            Toast.makeText(this, "Value acquired", Toast.LENGTH_SHORT).show()
        }else if( output2.text != ""){
            memoryVar = output2.text.toString().toDouble()
            Toast.makeText(this, "Value acquired", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this, "Cannot acquire this value", Toast.LENGTH_SHORT).show()

    }

}



