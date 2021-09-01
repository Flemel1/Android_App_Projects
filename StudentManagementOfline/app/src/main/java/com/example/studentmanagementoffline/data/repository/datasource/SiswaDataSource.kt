package com.example.studentmanagementoffline.data.repository.datasource

import com.example.studentmanagementoffline.data.model.KelasAndSiswa
import com.example.studentmanagementoffline.data.model.Siswa
import com.example.studentmanagementoffline.data.model.SiswaAndNilai

interface SiswaDataSource {
    suspend fun getKelasWithAllSiswa(namaKelas: String): List<KelasAndSiswa>
    suspend fun getSiswaWithAllNilai(): List<SiswaAndNilai>
    suspend fun insertSiswa(siswa: Siswa): Long
    suspend fun deleteSiswa(siswa: Siswa): Int
    suspend fun updateStudent(siswa: Siswa): Int
}