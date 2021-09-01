package com.example.studentmanagementoffline.domain.usecase

import com.example.studentmanagementoffline.data.model.MataPelajaranAndTugas
import com.example.studentmanagementoffline.data.model.Tugas
import com.example.studentmanagementoffline.domain.repository.StudentManagementRepo

class TugasUseCase(private val studentManagementRepo: StudentManagementRepo) {
    suspend fun getMapelWithAllTugas(): List<MataPelajaranAndTugas> =
        studentManagementRepo.getMapelWithAllTugas()

    suspend fun getAllTugas(): List<Tugas> = studentManagementRepo.getAllTugas()

    suspend fun insertTugas(tugas: Tugas): Long = studentManagementRepo.insertTugas(tugas)

    suspend fun deleteTugas(tugas: Tugas): Int = studentManagementRepo.deleteTugas(tugas)

    suspend fun updateTugas(tugas: Tugas): Int = studentManagementRepo.updateTugas(tugas)
}