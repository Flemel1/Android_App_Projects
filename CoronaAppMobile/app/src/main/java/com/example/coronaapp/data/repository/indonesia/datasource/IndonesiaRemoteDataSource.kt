package com.example.coronaapp.data.repository.indonesia.datasource

import com.example.coronaapp.data.entities.CoronaIndonesiaList
import com.example.coronaapp.data.entities.ProvinsiList
import retrofit2.Response

interface IndonesiaRemoteDataSource {
    suspend fun getAllIndonesiaCase() : Response<CoronaIndonesiaList>
    suspend fun getAllProvinceCase() : Response<ProvinsiList>
}