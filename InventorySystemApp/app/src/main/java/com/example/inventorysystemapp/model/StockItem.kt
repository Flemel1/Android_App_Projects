package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class StockItem(
    @SerializedName("id_barang")
    val idBarang: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("stock")
    val stock: String
)