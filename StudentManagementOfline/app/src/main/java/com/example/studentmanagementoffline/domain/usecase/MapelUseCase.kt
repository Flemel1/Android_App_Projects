package com.example.studentmanagementoffline.domain.usecase

import com.example.studentmanagementoffline.data.model.MataPelajaran
import com.example.studentmanagementoffline.domain.repository.StudentManagementRepo

class MapelUseCase(private val studentManagementRepo: StudentManagementRepo) {
    suspend fun getAllMapel(): List<MataPelajaran> = studentManagementRepo.getAllMapel()

    suspend fun insertMapel(mapel: MataPelajaran): Long = studentManagementRepo.insertMapel(mapel)

    suspend fun deleteMapel(mapel: MataPelajaran): Int = studentManagementRepo.deleteMapel(mapel)

    suspend fun updateMapel(mapel: MataPelajaran): Int = studentManagementRepo.updateMapel(mapel)
}