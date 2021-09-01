package com.example.studentmanagementoffline.app.di.subcomponent.module

import com.example.studentmanagementoffline.app.tugas.ViewModelFactoryTugas
import com.example.studentmanagementoffline.domain.usecase.TugasUseCase
import dagger.Module
import dagger.Provides

@Module
class ModuleTugas {

    @HomeFragmentScope
    @Provides
    fun provideViewModelFactoryTugas(tugasUseCase: TugasUseCase): ViewModelFactoryTugas {
        return ViewModelFactoryTugas(tugasUseCase)
    }
}