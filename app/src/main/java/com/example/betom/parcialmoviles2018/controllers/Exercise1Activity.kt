package com.example.betom.parcialmoviles2018.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.utilities.LOG_MESSAGE_HELLO_WORLD
import com.example.betom.parcialmoviles2018.utilities.LOG_TAG_HELLO_WORLD
import com.example.betom.parcialmoviles2018.utilities.TOAST_EXERCISE_1_ON_CLICK

class Exercise1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise1)
    }

    fun btnCLickeameClicked(view: View) {
        Log.d(LOG_TAG_HELLO_WORLD, LOG_MESSAGE_HELLO_WORLD)
        Toast.makeText(this, TOAST_EXERCISE_1_ON_CLICK,Toast.LENGTH_LONG).show()
    }
}
