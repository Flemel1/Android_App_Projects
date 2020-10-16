package com.example.broadcastreceivertutorial

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastreceivertutorial.receiver.AirplaneModeReceiver
import com.example.broadcastreceivertutorial.receiver.WifiStateReceiver

class MainActivity : AppCompatActivity() {

    lateinit var receiverAirplaneMode: AirplaneModeReceiver
    lateinit var receiverWifi: WifiStateReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiverAirplaneMode = AirplaneModeReceiver()
        receiverWifi = WifiStateReceiver()
        
        // check airplane mode is changed
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiverAirplaneMode, it)
        }

        // check wifi mode is changed
        IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION).let {
            registerReceiver(receiverWifi, it)
            val notificationManager : NotificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            receiverWifi.createNotificationChannel(notificationManager)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiverAirplaneMode)
        unregisterReceiver(receiverWifi)
    }
}