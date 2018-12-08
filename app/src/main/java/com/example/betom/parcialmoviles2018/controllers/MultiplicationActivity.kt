package com.example.betom.parcialmoviles2018.controllers

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.utilities.EXTRA_MULTIPLICATION_RESULT
import com.example.betom.parcialmoviles2018.utilities.INSTANCE_STATE_EXERCISE_2_NUMBER1
import com.example.betom.parcialmoviles2018.utilities.INSTANCE_STATE_EXERCISE_2_NUMBER2

class MultiplicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplication)

        restoreInstanceState(savedInstanceState)
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        if(savedInstanceState!=null) {
            val number1= findViewById<EditText>(R.id.txtProductNumber1)
            val number2= findViewById<EditText>(R.id.txtProductNumber2)

            val number1Value = savedInstanceState.getFloat(INSTANCE_STATE_EXERCISE_2_NUMBER1)
            val number2Value = savedInstanceState.getFloat(INSTANCE_STATE_EXERCISE_2_NUMBER2)

            number1.setText(number1Value.toString())
            number2.setText(number2Value.toString())
        }
    }

    fun btnExecuteMultiplicationClicked(view: View) {
        val number1= findViewById<EditText>(R.id.txtProductNumber1)
        val number2= findViewById<EditText>(R.id.txtProductNumber2)

        val a = number1.text.toString().trim().toFloat()
        val b = number2.text.toString().trim().toFloat()
        val c = a * b

        val intentRespuesta= Intent(this,Exercise2Activity::class.java)
        intentRespuesta.putExtra(EXTRA_MULTIPLICATION_RESULT,c)
        setResult(Activity.RESULT_OK,intentRespuesta)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val number1= findViewById<EditText>(R.id.txtProductNumber1)
        val number2= findViewById<EditText>(R.id.txtProductNumber2)

        saveInfo(number1, INSTANCE_STATE_EXERCISE_2_NUMBER1,outState)
        saveInfo(number2, INSTANCE_STATE_EXERCISE_2_NUMBER2,outState)
    }

    private fun saveInfo(number: EditText, tag: String, outState: Bundle) {
        val str = number.text.toString().trim()
        if(str.isNotEmpty()) {
            val value = str.toFloat()
            outState.putFloat(tag,value)
        }
    }
}
