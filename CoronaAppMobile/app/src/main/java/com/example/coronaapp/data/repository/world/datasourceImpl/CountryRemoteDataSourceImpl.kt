package com.example.coronaapp.data.repository.world.datasourceImpl

import com.example.coronaapp.data.api.CoronaService
import com.example.coronaapp.data.entities.CountryList
import com.example.coronaapp.data.entities.Died
import com.example.coronaapp.data.entities.Positif
import com.example.coronaapp.data.entities.Sembuh
import com.example.coronaapp.data.repository.world.datasource.CountryRemoteDataSource
import retrofit2.Response

class CountryRemoteDataSourceImpl(private val coronaService : CoronaService) : CountryRemoteDataSource {

    override suspend fun getAllWorldCase(): Response<CountryList> = coronaService.getAllWorldCase()

    override suspend fun getWorldPositiveCase(): Response<Positif> = coronaService.getWorldPositiveCase()

    override suspend fun getWorldRecoverCase(): Response<Sembuh> = coronaService.getWorldRecoverCase()

    override suspend fun getWorldDiedCase(): Response<Died> = coronaService.getWorldDiedCase()
}