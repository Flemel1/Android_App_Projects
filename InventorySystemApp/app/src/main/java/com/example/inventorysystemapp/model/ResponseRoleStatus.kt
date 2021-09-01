package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class ResponseRoleStatus(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)