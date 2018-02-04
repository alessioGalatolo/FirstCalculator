package com.example.galat.myapplication

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import kotlin.math.pow
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeNumberButtons()
        initializeOpButtons()
        initializeAdvButtons()
    }
    fun Double.roundTo2DecimalPlaces() =
            BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
    fun initializeNumberButtons(){
        fun addNumber(number: Long) {
            val shortOut = output1.text.toString().toLong()
            if (shortOut == 0L) {
                output1.setText(number.toString())
            } else {
                output1.setText((shortOut * 10L + number).toString())
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

    fun initializeOpButtons(){
        fun addSymbol(symbol: String) {
            when (output_sign.text) {
                "+" -> output2.text = (output2.text.toString().toDouble() + output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                "-" -> output2.text = (output2.text.toString().toDouble() - output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                "*" -> output2.text = (output2.text.toString().toDouble() * output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                "/" -> output2.text = (output2.text.toString().toDouble() / output1.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                "^" -> output2.text = (output2.text.toString().toDouble().pow(output1.text.toString().toDouble())).roundTo2DecimalPlaces().toString()
                "=" -> null
                else -> output2.text = output1.text
            }
            output1.setText("0")
            output_sign.text = symbol
            if (symbol == "√") {
                output2.text = sqrt(output2.text.toString().toDouble()).roundTo2DecimalPlaces().toString()
                output_sign.text = "="
            }
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
        b_power.setOnClickListener {
            addSymbol("^")
        }
        b_sqrt.setOnClickListener {
            addSymbol("√")
        }
    }

    fun initializeAdvButtons(){
        b_canc.setOnClickListener {
            if(output1.text.toString() != "0")
                output1.setText(output1.text.toString().subSequence(0, output1.text.toString().length - 1))
            else
                canc_all.performClick()
        }
        canc_all.setOnClickListener{
            output1.setText("0")
            output2.text = ""
            output_sign.text = ""
        }
    }
}

