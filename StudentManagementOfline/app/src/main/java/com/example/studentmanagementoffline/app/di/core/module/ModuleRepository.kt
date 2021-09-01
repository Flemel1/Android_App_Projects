package com.example.studentmanagementoffline.app.di.core.module

import com.example.studentmanagementoffline.data.repository.StudentManagementRepoImpl
import com.example.studentmanagementoffline.data.repository.datasource.*
import com.example.studentmanagementoffline.domain.repository.StudentManagementRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleRepository {

    @Singleton
    @Provides
    fun provideStudentManagementRepo(
        kelasDataSource: KelasDataSource,
        nilaiDataSource: NilaiDataSource,
        siswaDataSource: SiswaDataSource,
        tugasDataSource: TugasDataSource,
        mapelDataSource: MapelDataSource
    ): StudentManagementRepo {
        return StudentManagementRepoImpl(
            kelasDataSource,
            nilaiDataSource,
            siswaDataSource,
            tugasDataSource,
            mapelDataSource
        )
    }
}