package com.example.galat.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeNumberButton()
        initializeOpButton()
    }

    fun initializeNumberButton(){
        fun addNumber(number: Long) {
            val shortOut = output1.text.toString().toLong()
            if (shortOut == 0L) {
                output1.text = number.toString()
            } else {
                output1.text = (shortOut * 10L + number).toString()
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

    fun initializeOpButton(){
        fun addSymbol(symbol: String) {
            when(output_sign.text){
                "+" -> output2.text = (output2.text.toString().toDouble() + output1.text.toString().toInt()).toString()
                "-" -> output2.text = (output2.text.toString().toDouble() - output1.text.toString().toDouble()).toString()
                "*" -> output2.text = (output2.text.toString().toDouble() * output1.text.toString().toDouble()).toString()
                "/" -> output2.text = (output2.text.toString().toDouble() / output1.text.toString().toDouble()).toString()
                "=" -> null
                else -> output2.text = output1.text
            }
            output1.text = "0"
            output_sign.text = symbol
        }
        b_plus.setOnClickListener {
            addSymbol("+")
        }
        b_minus.setOnClickListener {
            addSymbol("-")
        }
        b_mult.setOnClickListener {
            addSymbol("*")
        }
        b_div.setOnClickListener {
            addSymbol("/")
        }
        b_equal.setOnClickListener {
            addSymbol("=")
        }
    }
}
