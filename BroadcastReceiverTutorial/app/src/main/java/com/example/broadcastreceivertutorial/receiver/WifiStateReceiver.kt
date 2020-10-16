package com.example.broadcastreceivertutorial.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.broadcastreceivertutorial.R

class WifiStateReceiver : BroadcastReceiver() {

    val TAG_WIFI_INFORMATION = "information"
    val CHANNEL_ID = "1"
    val CHANNEL_NAME = "Notification Wifi State"
    val CHANNEL_DESC = "Notification for wifi state changed"
    lateinit var notifManager : NotificationManager
    lateinit var mContentxt : Context

    override fun onReceive(context: Context?, intent: Intent?) {
        val isWifiStatus: Int = intent?.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN
        ) ?: return

        //retrieve context from MainActivity
        mContentxt = context!!

        if (isWifiStatus == WifiManager.WIFI_STATE_ENABLED) {
            Toast.makeText(context, "Wifi Enabled", Toast.LENGTH_SHORT).show()
            createNotification("Wifi Enabled")
        } else if (isWifiStatus == WifiManager.WIFI_STATE_DISABLED) {
            Toast.makeText(context, "Wifi Disabled", Toast.LENGTH_SHORT).show()
            createNotification("Wifi Disabled")
        }
    }

    //create notification
    private fun createNotification(wifiState : String) {
        val builder = NotificationCompat.Builder(mContentxt,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Wifi State")
            .setContentText("Wifi is $wifiState")
        notifManager.notify(0, builder.build())
    }

    //create notification channel for target SDK 26+
    fun createNotificationChannel(notificationManager: NotificationManager) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = CHANNEL_DESC
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notifManager = notificationManager
            notifManager.createNotificationChannel(channel)
        }
    }
}