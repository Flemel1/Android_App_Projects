package com.example.coronaapp.data.api

import com.example.coronaapp.data.entities.*
import com.example.coronaapp.data.util.Contract.Companion.API_URL
import retrofit2.Response
import retrofit2.http.GET

interface CoronaService {
    @GET("$API_URL/indonesia/")
    suspend fun getAllIndonesiaCase() : Response<CoronaIndonesiaList>

    @GET("$API_URL/indonesia/provinsi")
    suspend fun getAllProvinceCase() : Response<ProvinsiList>

    @GET(API_URL)
    suspend fun getAllWorldCase() : Response<CountryList>

    @GET("$API_URL/positif")
    suspend fun getWorldPositiveCase() : Response<Positif>

    @GET("$API_URL/sembuh")
    suspend fun getWorldRecoverCase() : Response<Sembuh>

    @GET("$API_URL/meninggal")
    suspend fun getWorldDiedCase() : Response<Died>
}