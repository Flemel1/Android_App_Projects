package com.example.studentmanagementoffline.data.repository.datasource

import com.example.studentmanagementoffline.data.model.MataPelajaranAndTugas
import com.example.studentmanagementoffline.data.model.Tugas

interface TugasDataSource {
    suspend fun getMapelWithAllTugas(): List<MataPelajaranAndTugas>
    suspend fun getAllTugas(): List<Tugas>
    suspend fun insertTugas(tugas: Tugas): Long
    suspend fun deleteTugas(tugas: Tugas): Int
    suspend fun updateTugas(tugas: Tugas): Int
}