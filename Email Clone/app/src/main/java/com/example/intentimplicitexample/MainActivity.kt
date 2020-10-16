package com.example.intentimplicitexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.intentimplicitexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val TAG = "intent"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.btnSend.setOnClickListener {
            Log.d(TAG, "create intent")
            val receiverName = binding.edSendTo.text.toString().split(',').toTypedArray()
            val emailTitle: String = binding.edEmailTitle.text.toString()
            val emailBody: String = binding.edEmailBody.text.toString()
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