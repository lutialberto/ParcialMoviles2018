package com.example.betom.parcialmoviles2018.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.utilities.Prefs
import kotlinx.android.synthetic.main.activity_exercise3.*
import android.os.AsyncTask



class Exercise3Activity : AppCompatActivity() {

    enum class CounterState {
        Stopped,Paused,Running
    }

    private inner class Counter : AsyncTask<Long, Void, Void>() {
        override fun doInBackground(vararg period: Long?): Void? {
            while (true) {
                publishProgress()
                if (isCancelled) break
                try {
                    period[0]?.let { Thread.sleep(it) }
                } catch (ie: InterruptedException) {
                    ie.printStackTrace()
                }
                count++
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
            updateUI()
        }
    }


    private lateinit var counter: Counter
    private var count = 0
    private var counterState = CounterState.Stopped

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise3)
    }

    override fun onResume() {
        super.onResume()
        initTimer()
        updateUI()
    }

    private fun updateUI() {
        txtCount.text = count.toString()
    }

    private fun initTimer() {
        counter = Counter()
        counterState = Prefs.getCounterState(this)
        count = if(counterState == CounterState.Stopped){
            0
        } else {
            Prefs.getPreviousCount(this)
        }
        if(counterState == CounterState.Running) {
            startTimer()
        }
    }

    private fun startTimer(){
        counterState = CounterState.Running
        counter.execute(1000L)
    }

    override fun onPause() {
        super.onPause()

        if(counterState == CounterState.Running) {
            counter.cancel(true)
        }

        Prefs.setPreviousCount(count,this)
        Prefs.setCounterState(counterState,this)
    }

    fun btnStartClicked(view: View) {
        startTimer()
    }

    fun btnStopClicked(view: View) {
        counterState = CounterState.Paused
        counter.cancel(true)
    }

    fun btnResetClicked(view: View) {
        counterState = CounterState.Stopped
        counter.cancel(true)
        count = 0
        updateUI()
    }
}
