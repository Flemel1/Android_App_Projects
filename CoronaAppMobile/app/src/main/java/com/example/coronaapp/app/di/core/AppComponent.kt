package com.example.coronaapp.app.di.core

import com.example.coronaapp.app.di.module.CoronaCaseSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
NetModule::class,
RemoteDataSourceModule::class,
RepositoryModule::class,
UseCaseModule::class])
interface AppComponent {

    fun coronaCaseSubComponent() : CoronaCaseSubComponent.Factory

}