package com.example.studentmanagementoffline.data.repository.datasource

import com.example.studentmanagementoffline.data.model.MataPelajaran

interface MapelDataSource {
    suspend fun getAllMapel(): List<MataPelajaran>
    suspend fun insertMapel(mapel: MataPelajaran): Long
    suspend fun deleteMapel(mapel: MataPelajaran): Int
    suspend fun updateMapel(mapel: MataPelajaran): Int
}