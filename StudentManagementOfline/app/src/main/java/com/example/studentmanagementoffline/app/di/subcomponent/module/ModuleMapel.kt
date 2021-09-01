package com.example.studentmanagementoffline.app.di.subcomponent.module

import com.example.studentmanagementoffline.app.mapel.ViewModelFactoryMapel
import com.example.studentmanagementoffline.domain.usecase.MapelUseCase
import dagger.Module
import dagger.Provides

@Module
class ModuleMapel {

    @HomeFragmentScope
    @Provides
    fun provideViewModelFactoryMapel(mapelUseCase: MapelUseCase): ViewModelFactoryMapel {
        return ViewModelFactoryMapel(mapelUseCase)
    }
}