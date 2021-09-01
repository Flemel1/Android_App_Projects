package com.example.coronaapp.app.di.core

import android.content.Context
import com.example.coronaapp.app.di.module.CoronaCaseSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [CoronaCaseSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext() : Context {
        return context.applicationContext
    }

}