package com.example.coronaapp.app.di.core

import com.example.coronaapp.domain.repository.IndonesiaCaseRepository
import com.example.coronaapp.domain.repository.WorldCaseRepository
import com.example.coronaapp.domain.usecase.IndonesiaCoronaUseCase
import com.example.coronaapp.domain.usecase.WorldCoronaUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideIndonesiaCoronaUseCase(indonesiaCaseRepository: IndonesiaCaseRepository)
        : IndonesiaCoronaUseCase {
        return IndonesiaCoronaUseCase(indonesiaCaseRepository)
    }

    @Provides
    fun provideWorldCoronaUseCase(worldCaseRepository: WorldCaseRepository)
        : WorldCoronaUseCase {
        return WorldCoronaUseCase(worldCaseRepository)
    }
}