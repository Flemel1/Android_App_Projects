package com.example.coronaapp.app.di.module

import com.example.coronaapp.app.indonesia.IndonesiaViewModelFactory
import com.example.coronaapp.app.world.WorldViewModelFactory
import com.example.coronaapp.domain.usecase.IndonesiaCoronaUseCase
import com.example.coronaapp.domain.usecase.WorldCoronaUseCase
import dagger.Module
import dagger.Provides

@Module
class CoronaCaseModule {

    @CoronaCaseScope
    @Provides
    fun provideIndonesiaViewModelFactory(indonesiaCoronaUseCase: IndonesiaCoronaUseCase)
        : IndonesiaViewModelFactory {
            return IndonesiaViewModelFactory(indonesiaCoronaUseCase)
    }

    @CoronaCaseScope
    @Provides
    fun provideWorldViewModelFactory(worldCoronaUseCase: WorldCoronaUseCase)
            : WorldViewModelFactory {
        return WorldViewModelFactory(worldCoronaUseCase)
    }

}