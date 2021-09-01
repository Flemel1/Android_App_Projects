package com.example.coronaapp.data.repository.indonesia.datasourceImpl

import com.example.coronaapp.data.api.CoronaService
import com.example.coronaapp.data.entities.CoronaIndonesiaList
import com.example.coronaapp.data.entities.ProvinsiList
import com.example.coronaapp.data.repository.indonesia.datasource.IndonesiaRemoteDataSource
import retrofit2.Response

class IndonesiaRemoteDataSourceImpl(private val coronaService: CoronaService) : IndonesiaRemoteDataSource {

    override suspend fun getAllIndonesiaCase(): Response<CoronaIndonesiaList> = coronaService.getAllIndonesiaCase()

    override suspend fun getAllProvinceCase(): Response<ProvinsiList> = coronaService.getAllProvinceCase()
}