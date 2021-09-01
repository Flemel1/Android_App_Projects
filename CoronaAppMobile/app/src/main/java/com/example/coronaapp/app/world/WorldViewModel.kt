package com.example.coronaapp.app.world

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coronaapp.data.entities.CountryList
import com.example.coronaapp.data.entities.Died
import com.example.coronaapp.data.entities.Positif
import com.example.coronaapp.data.entities.Sembuh
import com.example.coronaapp.domain.usecase.WorldCoronaUseCase

class WorldViewModel(
        private val worldCoronaUseCase: WorldCoronaUseCase
    ) : ViewModel() {

    private var positif : Positif?  = null
    private var sembuh : Sembuh? = null
    private var died : Died? = null

    fun getAllWorldCase() = liveData<CountryList> {
        val allWorldCase = worldCoronaUseCase.getAllWorldCase()
        emit(allWorldCase)
    }

    fun getWorldPositiveCase() = liveData<Positif> {
        if (positif == null) {
            positif = worldCoronaUseCase.getWorldPositiveCase()
        }
        emit(positif!!)
    }

    fun getWorldRecoverCase() = liveData<Sembuh> {
        if (sembuh == null) {
            sembuh = worldCoronaUseCase.getWorldRecoverCase()
        }
        emit(sembuh!!)
    }

    fun getWorldDiedCase() = liveData<Died> {
        if (died == null) {
            died = worldCoronaUseCase.getWorldDiedCase()
        }
        emit(died!!)
    }

}