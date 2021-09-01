package com.example.coronaapp.app.indonesia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coronaapp.data.entities.CoronaIndonesiaList
import com.example.coronaapp.data.entities.ProvinsiList
import com.example.coronaapp.domain.usecase.IndonesiaCoronaUseCase

class IndonesiaViewModel(
        private val indonesiaCoronaUseCase: IndonesiaCoronaUseCase
    ) : ViewModel() {

    var allIndonesiaCase = CoronaIndonesiaList()

    fun getAllIndonesiaCase() = liveData<CoronaIndonesiaList> {
        if (allIndonesiaCase.size == 0) {
            allIndonesiaCase =  indonesiaCoronaUseCase.getAllIndonesiaCase()
        }
        emit(allIndonesiaCase)
    }

    fun getAllProvinceCase() = liveData<ProvinsiList> {
        val allProvinceCase = indonesiaCoronaUseCase.getAllProvinceCase()
        emit(allProvinceCase)
    }
}