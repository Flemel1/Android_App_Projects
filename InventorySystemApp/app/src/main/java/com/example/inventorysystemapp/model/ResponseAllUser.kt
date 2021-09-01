package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class ResponseAllUser(
    @SerializedName("body")
    val body: List<User>
)