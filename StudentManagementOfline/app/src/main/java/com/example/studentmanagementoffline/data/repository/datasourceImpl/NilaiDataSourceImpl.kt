package com.example.studentmanagementoffline.data.repository.datasourceImpl

import com.example.studentmanagementoffline.data.db.StudentManagementDao
import com.example.studentmanagementoffline.data.model.Nilai
import com.example.studentmanagementoffline.data.model.MataPelajaranAndNilai
import com.example.studentmanagementoffline.data.repository.datasource.NilaiDataSource

class NilaiDataSourceImpl(private val studentManagementDao: StudentManagementDao) :
    NilaiDataSource {
    override suspend fun getNilaiWithAllMapel(): List<MataPelajaranAndNilai> =
        studentManagementDao.getNilaiWithAllMapel()

    override suspend fun insertNilai(nilai: Nilai): Long = studentManagementDao.insertNilai(nilai)

    override suspend fun deleteNilai(nilai: Nilai): Int = studentManagementDao.deleteNilai(nilai)
    override suspend fun getNilaiByStudentID(idSiswa: Int): List<Nilai> =
        studentManagementDao.getNilaiByStudentID(idSiswa)

    override suspend fun getAverageByStudent(): List<Double> =
        studentManagementDao.getAverageByStudent()
}