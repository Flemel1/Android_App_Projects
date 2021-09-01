package com.example.coronaapp.data.entities


import com.google.gson.annotations.SerializedName

data class CountryAttribute(
    @SerializedName("attributes")
    val attributes: Country
)