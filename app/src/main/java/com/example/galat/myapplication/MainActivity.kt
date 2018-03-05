package com.example.galat.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import kotlin.math.*


class MainActivity : AppCompatActivity() {
    var variabileDaNonDichiarare = 2
    var memoryVar = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeNumberButtons()
        initializeOpButtons()
        initializeAdvButtons()
    }

    fun Double.roundTo2DecimalPlaces() = BigDecimal(this).setScale(variabileDaNonDichiarare, BigDecimal.ROUND_HALF_UP).toDouble()

    fun initializeNumberButtons(){
        fun addNumber(number: Int) {
            if(output_sign.text == "=")
                output_sign.text = ""
            if(outputDot.visibility == View.VISIBLE){
                output1.text = (output1.text.toString().toDouble() + number.toDouble() * 10.0.pow(2 - (output1.text.toString().toDouble() % 1).toString().length)).toString()
                outputDot.visibility = View.INVISIBLE
                variabileDaNonDichiarare = 1
            }else if (output1.text.toString().toDouble() % 1 > 0.0){
                variabileDaNonDichiarare++
                output1.text = (output1.text.toString().toDouble() + number.toDouble() * 10.0.pow(1 - (output1.text.toString().toBigDecimal() % 1.toBigDecimal()).toString().length)).roundTo2DecimalPlaces().toString()
                outputDot.visibility = View.INVISIBLE
            }else{
                if (output1.text.toString().toDouble() == 0.0) {
                    output1.text = number.toString()
                } else {
                    output1.text = (output1.text.toString().toDouble() * 10.0 + number).toString()
                }
            }
            if(output1.text.toString().toDouble() % 1 == 0.0)
                output1.text = floor(output1.text.toString().toDouble()).roundToInt().toString()
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

    fun initializeOpButtons(){
        fun addSymbol(symbol: String) {
            when (output_sign.text) {
                "+" -> output2.text = (output2.text.toString().toDouble() + output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                "-" -> output2.text = (output2.text.toString().toDouble() - output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                "*" -> output2.text = (output2.text.toString().toDouble() * output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                "/" -> if(output1.text.toString().toDouble() != 0.0) {
                    output2.text = (output2.text.toString().toDouble() / output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                }else{
                    Toast.makeText(this,"Cannot divide by zero", Toast.LENGTH_SHORT).show()
                }
                "^" -> output2.text = (output2.text.toString().toDouble().pow(output1.text.toString().toDouble())).roundTo2DecimalPlaces().toString()
                "=" -> variabileDaNonDichiarare = 2
                else -> output2.text = output1.text
            }
            output_sign.text = symbol
//            if (symbol == "√") {
//                if(output1.text.toString().toDouble() == 0.0) {
//                    output2.text = sqrt(output2.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
//                }else
//                    output2.text = sqrt(output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
//                output_sign.text = "="
//            }else if( symbol == "^2" ){
//                if(output1.text.toString().toDouble() * 2 == 0.0)
//                    output2.text = output2.text.toString().toDouble().pow(2).roundTo2DecimalPlaces().toString()
//                else
//                    output2.text = output1.text.toString().toDouble().pow(2).roundTo2DecimalPlaces().toString()
//                output_sign.text = "="
//            }
            output1.text = "0"
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
                output2.text = output2.text.toString().toDouble().pow(2).roundTo2DecimalPlaces().toString()
            else
                output2.text = output1.text.toString().toDouble().pow(2).roundTo2DecimalPlaces().toString()
            output_sign.text = "="
            output1.text = "0"
            if(output2.text.toString().toDouble() % 1 == 0.0)
                output2.text = floor(output2.text.toString().toDouble()).roundToInt().toString()
        }
        b_sqrt.setOnClickListener {
            if(output1.text.toString().toDouble() * 2 == 0.0 && output2.text != "") {
                output2.text = sqrt(output2.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            }else
                output2.text = sqrt(output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
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
            "+" -> output2.text = (output2.text.toString().toDouble() + output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            "-" -> output2.text = (output2.text.toString().toDouble() - output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            "*" -> output2.text = (output2.text.toString().toDouble() * output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            "/" -> if(output1.text.toString().toDouble() != 0.0) {
                output2.text = (output2.text.toString().toDouble() / output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            }else{
                Toast.makeText(this,"Cannot divide by zero", Toast.LENGTH_SHORT).show()
            }
            "^" -> output2.text = (output2.text.toString().toDouble().pow(output1.text.toString().toDouble())).roundTo2DecimalPlaces().toString()
            "=" -> variabileDaNonDichiarare = 2
            else -> output2.text = output1.text
        }
        output_sign.text = "^"
        output1.text = "0"
        if(output2.text.toString().toDouble() % 1 == 0.0)
            output2.text = floor(output2.text.toString().toDouble()).roundToInt().toString()
    }

    fun initializeAdvButtons(){
        b_canc.setOnClickListener {
            if(output1.text.toString() != "0" && output1.text.toString().length > 1) {
                Toast.makeText(this, "${output1.text.toString().length}", Toast.LENGTH_SHORT).show()
                output1.text = output1.text.toString().subSequence(0, output1.text.toString().length - 1)
            }
            else
                canc_all.performClick()
        }
        canc_all.setOnClickListener{
            output1.text = "0"
            output2.text = ""
            output_sign.text = ""
            variabileDaNonDichiarare = 2
        }
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
            "log" -> if (output2.text.toString().toDouble() > 0)output2.text = log10(output2.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            else Toast.makeText(this, "Math error", Toast.LENGTH_SHORT).show()
            "tan" -> output2.text = tan(output2.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            "sin" -> output2.text = sin(output2.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            "cos" -> output2.text = cos(output2.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
            "ln"  -> if (output2.text.toString().toDouble() > 0)output2.text = log(output2.text.toString().toDouble(), E).roundTo2DecimalPlaces().toString()
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
            memoryVar = (memoryVar + output1.text.toString().toDouble()).roundTo2DecimalPlaces()
            output1.text = "0"
            output_sign.text = "="
        }else if(output2.text.toString().toDouble() != 0.0){
            memoryVar = (memoryVar + output2.text.toString().toDouble()).roundTo2DecimalPlaces()
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



