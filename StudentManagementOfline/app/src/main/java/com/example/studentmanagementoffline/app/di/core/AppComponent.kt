package com.example.studentmanagementoffline.app.di.core

import com.example.studentmanagementoffline.app.di.core.module.ModuleDataSource
import com.example.studentmanagementoffline.app.di.core.module.ModuleDatabase
import com.example.studentmanagementoffline.app.di.core.module.ModuleRepository
import com.example.studentmanagementoffline.app.di.core.module.ModuleUseCase
import com.example.studentmanagementoffline.app.di.subcomponent.SubComponentApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ModuleDatabase::class,
    ModuleDataSource::class,
    ModuleRepository::class,
    ModuleUseCase::class])
interface AppComponent {
    fun subComponentApp(): SubComponentApp.Factory
}