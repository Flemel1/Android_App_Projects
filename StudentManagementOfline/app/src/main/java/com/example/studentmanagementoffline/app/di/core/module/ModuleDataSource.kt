package com.example.studentmanagementoffline.app.di.core.module

import com.example.studentmanagementoffline.data.db.StudentManagementDao
import com.example.studentmanagementoffline.data.repository.datasource.*
import com.example.studentmanagementoffline.data.repository.datasourceImpl.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleDataSource {

    @Singleton
    @Provides
    fun provideKelasDataSource(studentManagementDao: StudentManagementDao): KelasDataSource {
        return KelasDataSourceImpl(studentManagementDao)
    }

    @Singleton
    @Provides
    fun provideMapelDataSource(studentManagementDao: StudentManagementDao): MapelDataSource {
        return MapelDataSourceImpl(studentManagementDao)
    }

    @Singleton
    @Provides
    fun provideNilaiDataSource(studentManagementDao: StudentManagementDao): NilaiDataSource {
        return NilaiDataSourceImpl(studentManagementDao)
    }

    @Singleton
    @Provides
    fun provideSiswaDataSource(studentManagementDao: StudentManagementDao): SiswaDataSource {
        return SiswaDataSourceImpl(studentManagementDao)
    }

    @Singleton
    @Provides
    fun provideTugasDataSource(studentManagementDao: StudentManagementDao): TugasDataSource {
        return TugasDataSourceImpl(studentManagementDao)
    }
}