package com.example.betom.parcialmoviles2018.controllers

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.utilities.EXTRA_MULTIPLICATION_RESULT

class MultiplicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplication)
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
}
