package com.example.studentmanagementoffline.app.di.core.module

import android.content.Context
import androidx.room.Room
import com.example.studentmanagementoffline.data.db.StudentManagementDao
import com.example.studentmanagementoffline.data.db.StudentManagementDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleDatabase {

    @Singleton
    @Provides
    fun provideStudentManagementDatabase(context: Context): StudentManagementDatabase {
        return Room.databaseBuilder(
            context,
            StudentManagementDatabase::class.java,
            "StudentManagement"
        ).build()
    }

    @Singleton
    @Provides
    fun provideStudentManagementDao(studentManagementDatabase: StudentManagementDatabase): StudentManagementDao {
        return studentManagementDatabase.studentManagementDao()
    }
}