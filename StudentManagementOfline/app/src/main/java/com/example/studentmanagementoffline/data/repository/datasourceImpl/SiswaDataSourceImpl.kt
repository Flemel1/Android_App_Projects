package com.example.studentmanagementoffline.data.repository.datasourceImpl

import com.example.studentmanagementoffline.data.db.StudentManagementDao
import com.example.studentmanagementoffline.data.model.KelasAndSiswa
import com.example.studentmanagementoffline.data.model.Siswa
import com.example.studentmanagementoffline.data.model.SiswaAndNilai
import com.example.studentmanagementoffline.data.repository.datasource.SiswaDataSource

class SiswaDataSourceImpl(private val studentManagementDao: StudentManagementDao) :
    SiswaDataSource {
    override suspend fun getKelasWithAllSiswa(namaKelas: String): List<KelasAndSiswa> =
        studentManagementDao.getKelasWithAllSiswa(namaKelas)

    override suspend fun getSiswaWithAllNilai(): List<SiswaAndNilai> =
        studentManagementDao.getSiswaWithAllNilai()

    override suspend fun insertSiswa(siswa: Siswa): Long = studentManagementDao.insertSiswa(siswa)

    override suspend fun deleteSiswa(siswa: Siswa): Int = studentManagementDao.deleteSiswa(siswa)
    override suspend fun updateStudent(siswa: Siswa): Int = studentManagementDao.updateStudent(siswa)
}