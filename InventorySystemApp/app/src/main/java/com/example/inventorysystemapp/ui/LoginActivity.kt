package com.example.inventorysystemapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.databinding.ActivityLoginBinding
import com.example.inventorysystemapp.factory.InventoryViewModelFactory
import com.example.inventorysystemapp.repository.InventoryRepository
import com.example.inventorysystemapp.utils.DataStoreUtil
import com.example.inventorysystemapp.viewmodel.InventoryViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginManager: DataStoreUtil
    private var username = ""
    private var password = ""
    private val repository: InventoryRepository = InventoryRepository()
    private val inventoryViewModel: InventoryViewModel by viewModels {
        InventoryViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        Log.d(TAG, inventoryViewModel.toString())
        loginManager = DataStoreUtil(applicationContext)
        userAlreadyLogged()
        binding.btnLogin.setOnClickListener {
            username = binding.textInputUsername.text.toString()
            password = binding.textInputPassword.text.toString()
            // login function
            inventoryViewModel.loginUser(username, password)
            inventoryViewModel.myStatusLogin.observe(this) {
                authenticationLogin(it.message, it.status)
            }
        }
    }

    private fun userAlreadyLogged() {
        loginManager.loginStatusFlow.asLiveData().observe(this) { isLogin ->
            if (isLogin) {
                val statusUser = loginManager.userStatusFlow.toString()
                val intentToMainActivity = Intent(this, MainActivity::class.java).apply {
                    putExtra("EXTRA_STATUS", statusUser)
                }
                startActivity(intentToMainActivity)
                finish()
            }
        }
    }

    private fun authenticationLogin(myRequestResponse: String, statusUser: String) {
        println(myRequestResponse)
        if (myRequestResponse == "valid") {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
            val intentToMainActivity = Intent(this, MainActivity::class.java).apply {
                putExtra("EXTRA_STATUS", statusUser)
            }
            lifecycleScope.launch {
                loginManager.setStatusLogin(true)
                loginManager.setStatusUser(statusUser)
            }
            startActivity(intentToMainActivity)
            finish()
        }
        else {
            Toast.makeText(this, "username atau password salah", Toast.LENGTH_SHORT).show()
        }
    }
}