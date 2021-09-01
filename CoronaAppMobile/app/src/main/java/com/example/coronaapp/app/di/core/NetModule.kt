package com.example.coronaapp.app.di.core

import com.example.coronaapp.data.api.CoronaService
import com.example.coronaapp.data.util.Contract.Companion.API_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(API_URL).build()
    }

    @Singleton
    @Provides
    fun provideCoronaService(retrofit: Retrofit) : CoronaService {
        return retrofit.create(CoronaService::class.java)
    }
}