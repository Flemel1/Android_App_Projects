package com.example.studentmanagementoffline.domain.usecase

import com.example.studentmanagementoffline.data.model.Nilai
import com.example.studentmanagementoffline.data.model.MataPelajaranAndNilai
import com.example.studentmanagementoffline.domain.repository.StudentManagementRepo

class NilaiUseCase(private val studentManagementRepo: StudentManagementRepo) {
    suspend fun getNilaiWithAllMapel(): List<MataPelajaranAndNilai> =
        studentManagementRepo.getNilaiWithAllMapel()

    suspend fun insertNilai(nilai: Nilai): Long = studentManagementRepo.insertNilai(nilai)

    suspend fun deleteNilai(nilai: Nilai): Int = studentManagementRepo.deleteNilai(nilai)

    suspend fun getNilaiByStudentID(idSiswa: Int): List<Nilai> =
        studentManagementRepo.getNilaiByStudentID(idSiswa)

    suspend fun getAverageByStudent(): List<Double> = studentManagementRepo.getAverageByStudent()
}