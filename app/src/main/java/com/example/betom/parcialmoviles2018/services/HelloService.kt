package com.example.betom.parcialmoviles2018.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.HandlerThread
import android.support.v4.content.LocalBroadcastManager



class HelloService : Service() {

    private lateinit var mServiceLooper: Looper
    private lateinit var mServiceHandler: ServiceHandler

    private inner class ServiceHandler(val context:Context,looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(3000)
                val customBroadcastIntent = Intent("broadcast para service id")
                customBroadcastIntent.putExtra("id service","iteracion nro ${msg.arg1} en service")
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(customBroadcastIntent)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("asdf", "Creating service")
        val thread = HandlerThread("ServiceStartArguments",
                android.os.Process.THREAD_PRIORITY_BACKGROUND)
        thread.start()

        mServiceLooper = thread.looper
        mServiceHandler = ServiceHandler(this,mServiceLooper)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val msg = mServiceHandler.obtainMessage()
        msg.arg1 = startId
        mServiceHandler.sendMessage(msg)
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d("asdf", "Intent destroyeded")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}