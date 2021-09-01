package com.example.studentmanagementoffline.data.repository.datasourceImpl

import com.example.studentmanagementoffline.data.db.StudentManagementDao
import com.example.studentmanagementoffline.data.model.MataPelajaranAndTugas
import com.example.studentmanagementoffline.data.model.Tugas
import com.example.studentmanagementoffline.data.repository.datasource.TugasDataSource

class TugasDataSourceImpl(private val studentManagementDao: StudentManagementDao) :
    TugasDataSource {
    override suspend fun getMapelWithAllTugas(): List<MataPelajaranAndTugas> =
        studentManagementDao.getMapelWithAllTugas()

    override suspend fun getAllTugas(): List<Tugas> = studentManagementDao.getAllTugas()

    override suspend fun insertTugas(tugas: Tugas): Long = studentManagementDao.insertTugas(tugas)

    override suspend fun deleteTugas(tugas: Tugas): Int = studentManagementDao.deleteTugas(tugas)

    override suspend fun updateTugas(tugas: Tugas): Int = studentManagementDao.updateTugas(tugas)
}