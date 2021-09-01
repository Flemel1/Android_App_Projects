package com.example.studentmanagementoffline.domain.usecase

import com.example.studentmanagementoffline.data.model.Kelas
import com.example.studentmanagementoffline.domain.repository.StudentManagementRepo

class KelasUseCase(private val studentManagementRepo: StudentManagementRepo) {
    suspend fun getAllKelas(): List<Kelas> = studentManagementRepo.getAllKelas()

    suspend fun insertKelas(kelas: Kelas): Long = studentManagementRepo.insertKelas(kelas)

    suspend fun deleteKelas(kelas: Kelas): Int = studentManagementRepo.deleteKelas(kelas)

    suspend fun getStudentTotalByClass(): List<Int> = studentManagementRepo.getStudentTotalByClass()
}