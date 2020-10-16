package com.example.loginui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginui.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var viewRoot : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        val viewRoot = binding.root
        setContentView(viewRoot)
        Toast.makeText(this, "Selamat anda berhasil login", Toast.LENGTH_SHORT).show()

        binding.btnStartService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                Log.d("service", "Service is running...")
            }
        }

        binding.btnSendData.setOnClickListener {
            Intent(this,MyService::class.java).also {
                var dataString = binding.edInput.text.toString()
                it.putExtra("KEY", dataString)
                startService(it)
            }
        }

        binding.btnStopService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                Log.d("service", "Serviceis stopped")
            }
        }
    }
}