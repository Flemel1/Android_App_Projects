package com.example.studentmanagementoffline.app.di.core.module

import com.example.studentmanagementoffline.domain.repository.StudentManagementRepo
import com.example.studentmanagementoffline.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleUseCase {

    @Singleton
    @Provides
    fun provideKelasUseCase(studentManagementRepo: StudentManagementRepo): KelasUseCase {
        return KelasUseCase(studentManagementRepo)
    }

    @Singleton
    @Provides
    fun provideMapelUseCase(studentManagementRepo: StudentManagementRepo): MapelUseCase {
        return MapelUseCase(studentManagementRepo)
    }

    @Singleton
    @Provides
    fun provideNilaiUseCase(studentManagementRepo: StudentManagementRepo): NilaiUseCase {
        return NilaiUseCase(studentManagementRepo)
    }

    @Singleton
    @Provides
    fun provideSiswaUseCase(studentManagementRepo: StudentManagementRepo): SiswaUseCase {
        return SiswaUseCase(studentManagementRepo)
    }

    @Singleton
    @Provides
    fun provideTugasUseCase(studentManagementRepo: StudentManagementRepo): TugasUseCase {
        return TugasUseCase(studentManagementRepo)
    }
}