package com.example.inventorysystemapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.inventorysystemapp.R
import com.example.inventorysystemapp.databinding.ActivityMainBinding
import com.example.inventorysystemapp.factory.InventoryViewModelFactory
import com.example.inventorysystemapp.repository.InventoryRepository
import com.example.inventorysystemapp.viewmodel.InventoryViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val navigationForAdmin = setOf(R.id.homeFragment, R.id.userFragment)

    private lateinit var navHostFragment : NavHostFragment
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding : ActivityMainBinding
    private lateinit var roleUserText : TextView
    private val repository : InventoryRepository = InventoryRepository()
    private val inventoryViewModel: InventoryViewModel by viewModels {
        InventoryViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        val statusUser: String? = intent.getStringExtra("EXTRA_STATUS")
        val headerView = binding.navView.getHeaderView(0)
        roleUserText = headerView.findViewById<TextView>(R.id.tv_status_role)
        if (statusUser.equals("Admin",true)) {
            createNavigationForAdmin()
        }
        else {
            createNavigationForOperator()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.myDrawerLayout)
    }

    override fun onBackPressed() {
        if (binding.myDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.myDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun createNavigationForAdmin() {
        binding.navView.menu.clear()
        binding.navView.inflateMenu(R.menu.navigation_menu_admin)
        appBarConfiguration = AppBarConfiguration(navigationForAdmin, binding.myDrawerLayout)
        setupActionBarWithNavController(navController, binding.myDrawerLayout)
        binding.navView.setupWithNavController(navController)
        roleUserText.text = "Admin"
        println(inventoryViewModel)
        inventoryViewModel.getAllStock().observe(this) {
            Log.d(TAG, it.toString())
        }
        inventoryViewModel.getAllUser().observe(this) {
            Log.d(TAG, it.toString())
        }
        inventoryViewModel.getCurrentIncome().observe(this) {
            Log.d(TAG, it.toString())
        }
        inventoryViewModel.getCurrentOutcome().observe(this) {
            Log.d(TAG, it.toString())
        }
    }

    private fun createNavigationForOperator() {
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.myDrawerLayout)
        setupActionBarWithNavController(navController, binding.myDrawerLayout)
        binding.navView.setupWithNavController(navController)
        println(inventoryViewModel)
        roleUserText.text = "Operator"
        inventoryViewModel.getAllIncome()
        inventoryViewModel.getAllOutcome()
        inventoryViewModel.getAllStock().observe(this) {
            Log.d(TAG, it.toString())
        }
        inventoryViewModel.getAllUser().observe(this) {
            Log.d(TAG, it.toString())
        }
        inventoryViewModel.getCurrentIncome().observe(this) {
            Log.d(TAG, it.toString())
        }
        inventoryViewModel.getCurrentOutcome().observe(this) {
            Log.d(TAG, it.toString())
        }
    }


}