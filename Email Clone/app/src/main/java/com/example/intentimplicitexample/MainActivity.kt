package com.example.intentimplicitexample

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.UserDictionary
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.loader.content.CursorLoader
import com.example.intentimplicitexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var ed_sendTo: EditText
    lateinit var ed_emailTitle: EditText
    lateinit var ed_emailBody: EditText
    lateinit var btn_send: Button
    val TAG = "intent"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed_sendTo = findViewById(R.id.ed_sendTo)
        ed_emailTitle = findViewById(R.id.ed_emailTitle)
        ed_emailBody = findViewById(R.id.ed_emailBody)
        btn_send = findViewById(R.id.btn_send)

        btn_send.setOnClickListener {
            Log.d(TAG, "create intent")
            val receiverName = ed_sendTo.text.toString().split(',').toTypedArray()
            val emailTitle: String = ed_emailTitle.text.toString()
            val emailBody: String = ed_emailBody.text.toString()
            val sendEmailIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_EMAIL, receiverName)
                putExtra(Intent.EXTRA_SUBJECT, emailTitle)
                putExtra(Intent.EXTRA_TEXT, emailBody)
                type = "text/plain"
                Log.d(TAG, "success created intent")
            }

            if (sendEmailIntent.resolveActivity(packageManager) != null) {
                startActivity(sendEmailIntent)
            }
        }
    }
}