package com.example.coronaapp.domain.usecase

import com.example.coronaapp.data.entities.CoronaIndonesiaList
import com.example.coronaapp.data.entities.ProvinsiList
import com.example.coronaapp.domain.repository.IndonesiaCaseRepository

class IndonesiaCoronaUseCase(private val indonesiaCaseRepository: IndonesiaCaseRepository) {
    suspend fun getAllIndonesiaCase() : CoronaIndonesiaList = indonesiaCaseRepository.getAllIndonesiaCase()
    suspend fun getAllProvinceCase() : ProvinsiList = indonesiaCaseRepository.getAllProvinceCase()
}