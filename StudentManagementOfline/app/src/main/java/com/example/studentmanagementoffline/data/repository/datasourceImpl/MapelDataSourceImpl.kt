package com.example.studentmanagementoffline.data.repository.datasourceImpl

import com.example.studentmanagementoffline.data.db.StudentManagementDao
import com.example.studentmanagementoffline.data.model.MataPelajaran
import com.example.studentmanagementoffline.data.repository.datasource.MapelDataSource

class MapelDataSourceImpl(private val studentManagementDao: StudentManagementDao) : MapelDataSource {
    override suspend fun getAllMapel(): List<MataPelajaran> = studentManagementDao.getAllMapel()

    override suspend fun insertMapel(mapel: MataPelajaran): Long = studentManagementDao.insertMapel(mapel)

    override suspend fun deleteMapel(mapel: MataPelajaran): Int = studentManagementDao.deleteMapel(mapel)

    override suspend fun updateMapel(mapel: MataPelajaran): Int = studentManagementDao.updateMapel(mapel)
}