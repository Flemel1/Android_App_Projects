package com.example.coronaapp.domain.usecase

import com.example.coronaapp.data.entities.CountryList
import com.example.coronaapp.data.entities.Died
import com.example.coronaapp.data.entities.Positif
import com.example.coronaapp.data.entities.Sembuh
import com.example.coronaapp.domain.repository.WorldCaseRepository

class WorldCoronaUseCase(private val worldCaseRepository: WorldCaseRepository) {
    suspend fun getAllWorldCase() : CountryList = worldCaseRepository.getAllWorldCase()
    suspend fun getWorldPositiveCase() : Positif = worldCaseRepository.getWorldPositiveCase()
    suspend fun getWorldRecoverCase() : Sembuh = worldCaseRepository.getWorldRecoverCase()
    suspend fun getWorldDiedCase() : Died = worldCaseRepository.getWorldDiedCase()
}