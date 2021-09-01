package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class ResponseDetailOutcome(
    @SerializedName("body")
    val body: List<DetailOutcome>
)