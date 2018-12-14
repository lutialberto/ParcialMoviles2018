package com.example.betom.parcialmoviles2018.controllers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.services.HelloService
import com.example.betom.parcialmoviles2018.services.MyIntentService
import kotlinx.android.synthetic.main.activity_exercise4.*

class Exercise4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise4)
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadCastReceiver, IntentFilter("broadcast para service id"))
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadCastReceiver, IntentFilter("broadcast para intent service id"))
    }

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            if(intent?.action=="broadcast para service id"){
                txtService.text = intent.getStringExtra("id service")
            }
            if(intent?.action=="broadcast para intent service id"){
                txtIntentService.text = intent.getStringExtra("id intent service")
            }
        }
    }

    fun btnServiceClicked(view: View){
        val intent0 = Intent(this,HelloService::class.java)
        intent0.putExtra("id",0)
        startService(intent0)
        val intent1 = Intent(this,HelloService::class.java)
        intent1.putExtra("id",1)
        startService(intent1)
        val intent2 = Intent(this,HelloService::class.java)
        intent2.putExtra("id",2)
        startService(intent2)
        val intent3 = Intent(this,HelloService::class.java)
        intent3.putExtra("id",3)
        startService(intent3)
    }

    fun btnIntentServiceClicked(view: View){
        val intent0 = Intent(this,MyIntentService::class.java)
        startService(intent0)
        val intent1 = Intent(this,MyIntentService::class.java)
        startService(intent1)
        val intent2 = Intent(this,MyIntentService::class.java)
        startService(intent2)
        val intent3 = Intent(this,MyIntentService::class.java)
        startService(intent3)
    }
}
