package com.example.footballscorecounter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private var mScoreTeamA = 0
    private var mScoreTeamB = 0
    private var mInputTeamA = MutableLiveData<String>("")
    private var mInputTeamB = MutableLiveData<String>("")

    fun setTeamName(teamA : String?, teamB : String?) {
        mInputTeamA.value = teamA!!
        mInputTeamB.value = teamB!!
    }

    fun increaseScoreTeamA() {
        mScoreTeamA++
    }

    fun decreaseScoreTeamA() {
        mScoreTeamA--
    }

    fun increaseScoreTeamB() {
        mScoreTeamB++
    }

    fun decreaseScoreTeamB() {
        mScoreTeamB--
    }

    fun getTeamA() : LiveData<String> {
        return mInputTeamA
    }

    fun getTeamB() : LiveData<String> {
        return mInputTeamB
    }

    fun getScoreTeamA() : Int {
        return mScoreTeamA
    }

    fun getScoreTeamB() : Int {
        return mScoreTeamB
    }
}