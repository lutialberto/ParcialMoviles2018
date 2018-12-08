package com.example.betom.parcialmoviles2018.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.utilities.Prefs
import kotlinx.android.synthetic.main.activity_exercise3.*
import java.util.*

class Exercise3Activity : AppCompatActivity() {

    enum class TimerState {
        Stopped,Paused,Running
    }

    private lateinit var timer: Timer
    private var count = 0
    private var timerState = TimerState.Stopped

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise3)
    }

    override fun onResume() {
        super.onResume()
        initTimer()
    }

    private fun initTimer() {
        timerState = Prefs.getTimerState(this)
        count = if(timerState == TimerState.Stopped){
            0
        } else {
            Prefs.getPreviousTimerCount(this)
        }
        if(timerState == TimerState.Running) {
            startTimer()
        }
        updateCountUI()
    }

    private fun updateCountUI() {
        txtCount.text = count.toString()
    }

    private fun startTimer(){
        timerState = TimerState.Running
        timer = object : Timer(){
        }
    }

    override fun onPause() {
        super.onPause()

        if(timerState == TimerState.Running) {
            timer.cancel()
        } else if (timer == TimerState.Paused) {

        }

        Prefs.setPreviousTimerCount(count,this)
        Prefs.setTimerState(timerState,this)
    }

    fun btnStartClicked(view: View) {
        startTimer()
        timerState = TimerState.Running
    }

    fun btnStopClicked(view: View) {
        timer.cancel()
        timerState = TimerState.Paused
    }

    fun btnResetClicked(view: View) {
        timer.cancel()
        timerState = TimerState.Stopped
        count = 0
    }
}
