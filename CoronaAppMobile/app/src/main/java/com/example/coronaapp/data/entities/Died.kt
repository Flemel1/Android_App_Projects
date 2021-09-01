package com.example.coronaapp.data.entities


import com.google.gson.annotations.SerializedName

data class Died(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)