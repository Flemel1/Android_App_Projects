package com.example.studentmanagementoffline.app.di.subcomponent.module

import com.example.studentmanagementoffline.app.kelas.ViewModelFactoryKelas
import com.example.studentmanagementoffline.domain.usecase.KelasUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleKelas {

    @HomeFragmentScope
    @Provides
    fun provideViewModelFactoryKelas(kelasUseCase: KelasUseCase): ViewModelFactoryKelas {
        return ViewModelFactoryKelas(kelasUseCase)
    }
}