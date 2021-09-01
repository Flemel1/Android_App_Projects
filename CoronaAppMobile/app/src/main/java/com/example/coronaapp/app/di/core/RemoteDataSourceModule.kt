package com.example.coronaapp.app.di.core

import com.example.coronaapp.data.api.CoronaService
import com.example.coronaapp.data.repository.indonesia.datasource.IndonesiaRemoteDataSource
import com.example.coronaapp.data.repository.indonesia.datasourceImpl.IndonesiaRemoteDataSourceImpl
import com.example.coronaapp.data.repository.world.datasource.CountryRemoteDataSource
import com.example.coronaapp.data.repository.world.datasourceImpl.CountryRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideIndonesiaRemoteDataSource(coronaService: CoronaService)
        : IndonesiaRemoteDataSource {
        return IndonesiaRemoteDataSourceImpl(coronaService)
    }

    @Singleton
    @Provides
    fun provideCountryRemoteDataSource(coronaService: CoronaService)
        : CountryRemoteDataSource {
        return CountryRemoteDataSourceImpl(coronaService)
    }
}