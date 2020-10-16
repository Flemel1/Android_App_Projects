package com.example.footballscorecounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.footballscorecounter.HomeScreen.Companion.EXTRA_TEAM_A
import com.example.footballscorecounter.HomeScreen.Companion.EXTRA_TEAM_B
import com.example.footballscorecounter.viewmodel.SharedViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent : Intent = getIntent()
        val sharedViewModel : SharedViewModel = ViewModelProvider(this).get(
            SharedViewModel::class.java
        )
        val teamA : String? = intent.getStringExtra(EXTRA_TEAM_A)
        val teamB : String? = intent.getStringExtra(EXTRA_TEAM_B)
        sharedViewModel.setTeamName(teamA, teamB)
    }
}