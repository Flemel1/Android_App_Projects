package com.example.coronaapp.app.world

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronaapp.domain.usecase.WorldCoronaUseCase

class WorldViewModelFactory(
        private val worldCoronaUseCase: WorldCoronaUseCase
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        lateinit var worldViewModel: WorldViewModel
        if (modelClass.isAssignableFrom(WorldViewModel::class.java)) {
            worldViewModel =  WorldViewModel(worldCoronaUseCase)
        }
        return worldViewModel as T
    }
}