package com.example.coronaapp.domain.repository

import com.example.coronaapp.data.entities.CoronaIndonesiaList
import com.example.coronaapp.data.entities.ProvinsiList

interface IndonesiaCaseRepository {
    suspend fun getAllIndonesiaCase() : CoronaIndonesiaList
    suspend fun getAllProvinceCase() : ProvinsiList
}