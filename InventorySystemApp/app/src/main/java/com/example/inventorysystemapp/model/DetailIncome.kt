package com.example.inventorysystemapp.model


import com.google.gson.annotations.SerializedName

data class DetailIncome(
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("nama_barang")
    val namaBarang: String,
    @SerializedName("receiver")
    val `receiver`: String,
    @SerializedName("stock")
    val stock: String
)