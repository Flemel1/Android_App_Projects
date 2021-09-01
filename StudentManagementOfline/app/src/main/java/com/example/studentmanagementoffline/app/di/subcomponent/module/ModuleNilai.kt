package com.example.studentmanagementoffline.app.di.subcomponent.module

import com.example.studentmanagementoffline.app.nilai.ViewModelFactoryNilai
import com.example.studentmanagementoffline.domain.usecase.NilaiUseCase
import dagger.Module
import dagger.Provides

@Module
class ModuleNilai {

    @HomeFragmentScope
    @Provides
    fun provideViewModelFactoryNilai(nilaiUseCase: NilaiUseCase): ViewModelFactoryNilai {
        return ViewModelFactoryNilai(nilaiUseCase)
    }
}