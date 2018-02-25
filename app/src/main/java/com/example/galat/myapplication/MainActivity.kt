package com.example.galat.myapplication

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeNumberButtons()
        initializeOpButtons()
        initializeAdvButtons()
    }
//    fun Double.checkDecimals(){
//        if (this.toBigDecimal() == 0.toBigDecimal()){
//            BigDecimal(this).setScale(0, BigDecimal.ROUND_HALF_UP).toDouble()
//        }else
//            BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
//    }
    fun Double.roundTo2DecimalPlaces() =
            BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()            // to be deleted

    fun initializeNumberButtons(){
        fun addNumber(number: Int) {
            if(output_sign.text == "=")
                output_sign.text = ""
            if(outputDot.visibility == View.VISIBLE){
                output1.text = (output1.text.toString().toDouble() + number.toDouble() * 10.0.pow(2 - (output1.text.toString().toDouble() % 1).toString().length)).toString()
                outputDot.visibility = View.INVISIBLE
            }else if (output1.text.toString().toDouble() % 1 > 0.0){
                Toast.makeText(this, "${(output1.text.toString().toDouble() % 1.0)}", Toast.LENGTH_LONG).show()
                output1.text = (output1.text.toString().toDouble() + number.toDouble() * 10.0.pow(1 - (output1.text.toString().toDouble() % 1).toString().length)).toString()
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
//            else
//                Toast.makeText(this, "floor ${floor(output1.text.toString().toDouble())} big ${0.toBigDecimal()}", Toast.LENGTH_LONG).show()
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
                            Toast.makeText(this, "${output1.text.toString().toDouble()}", Toast.LENGTH_LONG).show()
                            output2.text = (output2.text.toString().toDouble() / output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                        }else{
                            Toast.makeText(this,"Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        }
                "=" -> null
                else -> output2.text = output1.text
            }
            output_sign.text = symbol
            if (symbol == "√") {
                if(output1.text.toString().toDouble() * 2 == 0.0) {
                    output2.text = sqrt(output2.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                }else
                    output2.text = sqrt(output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                output_sign.text = "="
            }else if( symbol == "^2" ){
                if(output1.text.toString().toDouble() * 2 == 0.0)
                    output2.text = output2.text.toString().toDouble().pow(2).roundTo2DecimalPlaces().toString()
                else
                    output2.text = output1.text.toString().toDouble().pow(2).roundTo2DecimalPlaces().toString()
                output_sign.text = "="
            }
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
            addSymbol("^2")
        }
        b_sqrt.setOnClickListener {
            addSymbol("√")
        }
        b_dot.setOnClickListener {
            outputDot.visibility = View.VISIBLE
        }
    }

    fun initializeAdvButtons(){
        b_canc.setOnClickListener {
            if(output1.text.toString() != "0")
                output1.text = output1.text.toString().subSequence(0, output1.text.toString().length - 1)
            else
                canc_all.performClick()
        }
        canc_all.setOnClickListener{
            output1.text = "0"
            output2.text = ""
            output_sign.text = ""
        }
    }
}

