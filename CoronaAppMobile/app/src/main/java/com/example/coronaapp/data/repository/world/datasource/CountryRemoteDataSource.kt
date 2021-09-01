package com.example.coronaapp.data.repository.world.datasource

import com.example.coronaapp.data.entities.CountryList
import com.example.coronaapp.data.entities.Died
import com.example.coronaapp.data.entities.Positif
import com.example.coronaapp.data.entities.Sembuh
import retrofit2.Response

interface CountryRemoteDataSource {
    suspend fun getAllWorldCase() : Response<CountryList>
    suspend fun getWorldPositiveCase() : Response<Positif>
    suspend fun getWorldRecoverCase() : Response<Sembuh>
    suspend fun getWorldDiedCase() : Response<Died>
}