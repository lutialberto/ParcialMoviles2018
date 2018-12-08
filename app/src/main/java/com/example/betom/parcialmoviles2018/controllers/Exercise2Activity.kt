package com.example.betom.parcialmoviles2018.controllers

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.utilities.EXTRA_MULTIPLICATION_RESULT
import com.example.betom.parcialmoviles2018.utilities.REQUEST_MULTIPLICATION

class Exercise2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise2)
    }

    fun btnGetContactClicked(view: View) {

    }

    fun btnMultiplicationClicked(view: View) {
        val exercise1Activity= Intent(this,MultiplicationActivity::class.java)
        startActivityForResult(exercise1Activity,REQUEST_MULTIPLICATION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_MULTIPLICATION && resultCode == Activity.RESULT_OK) {
            val multiplicationResult = data!!.getFloatExtra(EXTRA_MULTIPLICATION_RESULT, 0F)
            val txtResult = findViewById<TextView>(R.id.txtMultiplicationResult)
            txtResult.text = multiplicationResult.toString()
        }
    }
}
