package com.example.betom.parcialmoviles2018.services

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log

class MyIntentService : IntentService("MyService") {
    private var startId=-1

    override fun onCreate() {
        super.onCreate()
        Log.d("asdf", "Creating intent service")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        this.startId=startId
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {
        try {
            Thread.sleep(3000)
            val customBroadcastIntent = Intent("broadcast para intent service id")
            customBroadcastIntent.putExtra("id intent service","iteracion nro $startId en intent service")
            LocalBroadcastManager.getInstance(this)
                    .sendBroadcast(customBroadcastIntent)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
}

