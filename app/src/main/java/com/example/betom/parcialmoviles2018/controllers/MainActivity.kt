package com.example.betom.parcialmoviles2018.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.betom.parcialmoviles2018.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnExercise1Click(view : View){
        val exercise1Activity=Intent(this,Exercise1Activity::class.java)
        startActivity(exercise1Activity)
    }

    fun btnExercise2Click(view : View){
        val exercise1Activity=Intent(this,Exercise2Activity::class.java)
        startActivity(exercise1Activity)
    }

    fun btnExercise3Click(view : View){
        val exercise1Activity=Intent(this,Exercise3Activity::class.java)
        startActivity(exercise1Activity)
    }

    fun btnExercise4Click(view : View){

    }

    fun btnExercise5Click(view : View){

    }

}
