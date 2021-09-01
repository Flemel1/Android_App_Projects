package com.example.coronaapp.data.entities


import com.google.gson.annotations.SerializedName

data class Sembuh(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)