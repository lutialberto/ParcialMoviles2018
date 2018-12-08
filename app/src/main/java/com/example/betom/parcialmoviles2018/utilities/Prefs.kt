package com.example.betom.parcialmoviles2018.utilities

import android.content.Context
import android.icu.text.RelativeDateTimeFormatter
import android.preference.PreferenceManager
import com.example.betom.parcialmoviles2018.controllers.Exercise3Activity
import java.util.prefs.Preferences

class Prefs {
    companion object {

        private const val PREVIOUS_TIMER_COUNT = "conteo anterior"

        fun getPreviousTimerCount(context: Context): Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PREVIOUS_TIMER_COUNT,0)
        }

        fun setPreviousTimerCount(seconds:Int,context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREVIOUS_TIMER_COUNT,seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "estado anterior"

        fun getTimerState(context: Context): Exercise3Activity.TimerState {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID,0)
            return Exercise3Activity.TimerState.values()[ordinal]
        }

        fun setTimerState(state: Exercise3Activity.TimerState,context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal =state.ordinal
            editor.putInt(TIMER_STATE_ID,ordinal)
            editor.apply()
        }
    }
}