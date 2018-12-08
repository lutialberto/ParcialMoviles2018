package com.example.betom.parcialmoviles2018.controllers

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.utilities.*

class Exercise2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise2)

        restoreInstanceState(savedInstanceState)
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        if(savedInstanceState!=null) {
            val result= findViewById<TextView>(R.id.txtMultiplicationResult)
            val dataContact= findViewById<TextView>(R.id.txtContactInformation)

            val resultValue = savedInstanceState.getString(INSTANCE_STATE_EXERCISE_2_RESULT)
            val dataContactValue = savedInstanceState.getString(INSTANCE_STATE_EXERCISE_2_CONTACT_INFO)

            result.text = resultValue
            dataContact.text = dataContactValue
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val result = findViewById<TextView>(R.id.txtMultiplicationResult)
        val dataContact = findViewById<TextView>(R.id.txtContactInformation)

        saveInfo(result, INSTANCE_STATE_EXERCISE_2_RESULT,outState)
        saveInfo(dataContact, INSTANCE_STATE_EXERCISE_2_CONTACT_INFO,outState)
    }

    private fun saveInfo(textView: TextView, tag: String, outState: Bundle) {
        val value = textView.text.toString().trim()
        if(value.isNotEmpty()) {
            outState.putString(tag,value)
        }
    }
}
