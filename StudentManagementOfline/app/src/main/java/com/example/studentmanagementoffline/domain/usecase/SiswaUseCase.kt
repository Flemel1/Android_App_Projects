package com.example.studentmanagementoffline.domain.usecase

import com.example.studentmanagementoffline.data.model.KelasAndSiswa
import com.example.studentmanagementoffline.data.model.Siswa
import com.example.studentmanagementoffline.data.model.SiswaAndNilai
import com.example.studentmanagementoffline.domain.repository.StudentManagementRepo

class SiswaUseCase(private val studentManagementRepo: StudentManagementRepo) {
    suspend fun getKelasWithAllSiswa(namaKelas: String): List<KelasAndSiswa> =
        studentManagementRepo.getKelasWithAllSiswa(namaKelas)

    suspend fun getSiswaWithAllNilai(): List<SiswaAndNilai> =
        studentManagementRepo.getSiswaWithAllNilai()

    suspend fun insertSiswa(siswa: Siswa): Long = studentManagementRepo.insertSiswa(siswa)

    suspend fun deleteSiswa(siswa: Siswa): Int = studentManagementRepo.deleteSiswa(siswa)

    suspend fun updateStudent(siswa: Siswa): Int = studentManagementRepo.updateStudent(siswa)
}