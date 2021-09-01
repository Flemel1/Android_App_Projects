package com.example.studentmanagementoffline.app.di.subcomponent.module

import com.example.studentmanagementoffline.app.siswa.ViewModelFactorySiswa
import com.example.studentmanagementoffline.domain.usecase.SiswaUseCase
import dagger.Module
import dagger.Provides

@Module
class ModuleSiswa {

    @HomeFragmentScope
    @Provides
    fun provideViewModelFactorySiswa(siswaUseCase: SiswaUseCase): ViewModelFactorySiswa {
        return ViewModelFactorySiswa(siswaUseCase)
    }
}