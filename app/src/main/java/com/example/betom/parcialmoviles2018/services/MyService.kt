package com.example.betom.parcialmoviles2018.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message

class MyService : Service() {

    private lateinit var mServiceLooper : Looper
    private lateinit var mServiceHandler : ServiceHandler

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    class ServiceHandler : Handler {
        fun ServiceHandler(looper:Looper){
            super(looper)
        }

        override fun handleMessage(msg: Message?) {
            try{
                Thread.sleep(5000)
            }
            catch (e: InterruptedException){
                Thread.currentThread().interrupt()
            }
            stopSelf(msg!!.arg1)
        }
    }
}
