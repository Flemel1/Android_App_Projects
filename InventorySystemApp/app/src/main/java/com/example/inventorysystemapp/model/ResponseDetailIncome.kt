package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class ResponseDetailIncome(
    @SerializedName("body")
    val body: List<DetailIncome>
)