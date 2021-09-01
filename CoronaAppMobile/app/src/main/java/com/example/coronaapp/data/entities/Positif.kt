package com.example.coronaapp.data.entities


import com.google.gson.annotations.SerializedName

data class Positif(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)