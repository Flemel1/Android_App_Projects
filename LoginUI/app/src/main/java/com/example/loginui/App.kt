package com.example.loginui

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.content.getSystemService

class App: Application() {
    val CHANNEL_ID: String = "exampleNotificationID"
    override fun onCreate() {
        super.onCreate()
        createNotificationID()
    }

    private fun createNotificationID() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) run {
            var serviceChannel: NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Example Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            var manager: NotificationManager = this!!.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
            Log.d("App", "Channel created")
        }
    }
}