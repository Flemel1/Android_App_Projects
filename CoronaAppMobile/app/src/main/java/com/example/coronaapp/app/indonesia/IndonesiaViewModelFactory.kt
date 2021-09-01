package com.example.coronaapp.app.indonesia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronaapp.domain.usecase.IndonesiaCoronaUseCase

class IndonesiaViewModelFactory(
        private val indonesiaCoronaUseCase: IndonesiaCoronaUseCase
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IndonesiaViewModel::class.java)) {
            return IndonesiaViewModel(indonesiaCoronaUseCase) as T
        }
        throw IllegalArgumentException("Uknown View Model Class")
    }
}