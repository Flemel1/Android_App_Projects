package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class DetailOutcome(
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("nama_barang")
    val namaBarang: String,
    @SerializedName("sender")
    val sender: String,
    @SerializedName("stock")
    val stock: String
)