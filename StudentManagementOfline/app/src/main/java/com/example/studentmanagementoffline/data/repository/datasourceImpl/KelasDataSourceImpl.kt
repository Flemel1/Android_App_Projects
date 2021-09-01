package com.example.studentmanagementoffline.data.repository.datasourceImpl

import com.example.studentmanagementoffline.data.db.StudentManagementDao
import com.example.studentmanagementoffline.data.model.Kelas
import com.example.studentmanagementoffline.data.repository.datasource.KelasDataSource

class KelasDataSourceImpl(private val studentManagementDao: StudentManagementDao): KelasDataSource {
    override suspend fun getAllKelas(): List<Kelas> = studentManagementDao.getAllKelas()

    override suspend fun insertKelas(kelas: Kelas): Long = studentManagementDao.insertKelas(kelas)

    override suspend fun deleteKelas(kelas: Kelas): Int = studentManagementDao.deleteKelas(kelas)
    override suspend fun getStudentTotalByClass(): List<Int> = studentManagementDao.getStudentTotalByClass()
}