package com.example.coronaapp.app.di.core

import com.example.coronaapp.data.repository.indonesia.IndonesiaCaseRepositoryImpl
import com.example.coronaapp.data.repository.indonesia.datasource.IndonesiaRemoteDataSource
import com.example.coronaapp.data.repository.world.WorldCaseRepositoryImpl
import com.example.coronaapp.data.repository.world.datasource.CountryRemoteDataSource
import com.example.coronaapp.domain.repository.IndonesiaCaseRepository
import com.example.coronaapp.domain.repository.WorldCaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideIndonesiaCaseRepository(indonesiaRemoteDataSource: IndonesiaRemoteDataSource)
        : IndonesiaCaseRepository {
        return IndonesiaCaseRepositoryImpl(indonesiaRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideWorldCaseRepository(countryRemoteDataSource: CountryRemoteDataSource)
        : WorldCaseRepository {
        return WorldCaseRepositoryImpl(countryRemoteDataSource)
    }
}