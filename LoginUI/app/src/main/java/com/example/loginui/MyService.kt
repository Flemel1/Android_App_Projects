package com.example.loginui

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class MyService : Service() {

    val CHANNEL_ID: String = "exampleNotificationID"

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        Toast.makeText(this,"Service created", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val text: String? = intent.getStringExtra("KEY")
        text?.let {
            Log.d("service", text)
        }
        //keep running service in background

        //onTaskRemoved(intent)

        //delete code below to keep running service in background
        //keep running service in foreground with notification

//        var secondIntent: Intent = Intent(this, HomeScreen::class.java)
//        var intentPending: PendingIntent = PendingIntent.getActivity(
//            this,
//            0,
//            secondIntent,
//            0
//        )

        //create notification

//        var notif: Notification = NotificationCompat.Builder(this,CHANNEL_ID)
//            .setContentTitle("Example Notification")
//            .setContentText(text)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentIntent(intentPending)
//            .build()
//
//        startForeground(1,notif)

        //Toast.makeText(this,text, Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    //keep running service in background
    override fun onTaskRemoved(rootIntent: Intent?) {
        startService(rootIntent)
        super.onTaskRemoved(rootIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("service", "service is stopped manually")
    }
}
