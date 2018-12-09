package com.example.betom.parcialmoviles2018.utilities

import android.content.Context
import android.preference.PreferenceManager
import com.example.betom.parcialmoviles2018.controllers.Exercise3Activity

class Prefs {
    companion object {

        private const val PREVIOUS_TIMER_COUNT = "conteo anterior"

        fun getPreviousCount(context: Context): Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PREVIOUS_TIMER_COUNT,0)
        }

        fun setPreviousCount(seconds:Int, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREVIOUS_TIMER_COUNT,seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "estado anterior"

        fun getCounterState(context: Context): Exercise3Activity.CounterState {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID,0)
            return Exercise3Activity.CounterState.values()[ordinal]
        }

        fun setCounterState(state: Exercise3Activity.CounterState, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal =state.ordinal
            editor.putInt(TIMER_STATE_ID,ordinal)
            editor.apply()
        }
    }
}