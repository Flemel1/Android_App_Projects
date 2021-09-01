package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class ResponseGetAllStock(
    @SerializedName("body")
    val body: ArrayList<StockItem>,
    @SerializedName("itemCount")
    val itemCount: Int
)