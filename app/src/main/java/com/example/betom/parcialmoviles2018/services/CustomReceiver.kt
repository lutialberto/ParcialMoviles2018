package com.example.betom.parcialmoviles2018.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CustomReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1!=null && p1.action == "manda hello service"){

        }
    }
}