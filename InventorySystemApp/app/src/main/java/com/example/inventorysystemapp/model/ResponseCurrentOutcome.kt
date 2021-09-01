package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class ResponseCurrentOutcome(
    @SerializedName("currentOutcome")
    val currentOutcome: Int
)