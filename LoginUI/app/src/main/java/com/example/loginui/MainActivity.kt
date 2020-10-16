package com.example.loginui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewRoot: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewRoot = binding.root
        setContentView(viewRoot)
        binding.btnLogin.setOnClickListener(View.OnClickListener {
            val username = binding.edUsername.text.trim().toString()
            val password: String = binding.edPassword.text.trim().toString()
            if (username.contentEquals("admin") && password.contentEquals("admin")) {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Username atau Password Anda tidak benar!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}