package com.example.coronaapp.domain.repository

import com.example.coronaapp.data.entities.CountryList
import com.example.coronaapp.data.entities.Died
import com.example.coronaapp.data.entities.Positif
import com.example.coronaapp.data.entities.Sembuh

interface WorldCaseRepository {
    suspend fun getAllWorldCase() : CountryList
    suspend fun getWorldPositiveCase() : Positif
    suspend fun getWorldRecoverCase() : Sembuh
    suspend fun getWorldDiedCase() : Died
}