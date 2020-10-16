package com.example.footballscorecounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class HomeScreen : AppCompatActivity() {

    val TAG = "HomeScreen"
    lateinit var inputTeamA : EditText
    lateinit var inputTeamB : EditText
    lateinit var startButton : Button

    companion object {
        const val EXTRA_TEAM_A = "teamA"
        const val EXTRA_TEAM_B = "teamB"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        inputTeamA = findViewById(R.id.input_team_A)
        inputTeamB = findViewById(R.id.input_team_B)
        startButton = findViewById(R.id.btn_start)
        startButton.setOnClickListener {
            inputTeamName()
        }
    }

    private fun inputTeamName() {
        var teamA = inputTeamA.text.toString()
        var teamB = inputTeamB.text.toString()
        if (!teamA.isNullOrEmpty() && !teamB.isNullOrEmpty()) {
            Log.d(TAG,teamA)
            Log.d(TAG,teamB)
            Intent(this, MainActivity::class.java).also {
                it.putExtra(EXTRA_TEAM_A,teamA)
                it.putExtra(EXTRA_TEAM_B,teamB)
                startActivity(it)
            }
        }
    }
}