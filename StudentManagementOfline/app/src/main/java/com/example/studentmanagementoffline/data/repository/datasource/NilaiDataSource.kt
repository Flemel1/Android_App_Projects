package com.example.studentmanagementoffline.data.repository.datasource

import com.example.studentmanagementoffline.data.model.Nilai
import com.example.studentmanagementoffline.data.model.MataPelajaranAndNilai

interface NilaiDataSource {
    suspend fun getNilaiWithAllMapel(): List<MataPelajaranAndNilai>
    suspend fun insertNilai(nilai: Nilai): Long
    suspend fun deleteNilai(nilai: Nilai): Int
    suspend fun getNilaiByStudentID(idSiswa: Int): List<Nilai>
    suspend fun getAverageByStudent(): List<Double>
}