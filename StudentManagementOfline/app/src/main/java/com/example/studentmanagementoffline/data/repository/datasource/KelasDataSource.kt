package com.example.studentmanagementoffline.data.repository.datasource

import com.example.studentmanagementoffline.data.model.Kelas

interface KelasDataSource {
    suspend fun getAllKelas(): List<Kelas>
    suspend fun insertKelas(kelas: Kelas): Long
    suspend fun deleteKelas(kelas: Kelas): Int
    suspend fun getStudentTotalByClass(): List<Int>
}