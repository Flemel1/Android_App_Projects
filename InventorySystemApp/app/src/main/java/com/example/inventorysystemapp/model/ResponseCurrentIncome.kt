package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class ResponseCurrentIncome(
    @SerializedName("currentIncome")
    val currentIncome: Int
)