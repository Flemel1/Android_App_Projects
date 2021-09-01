package com.example.studentmanagementoffline.domain.repository

import com.example.studentmanagementoffline.data.model.*

interface StudentManagementRepo {

    suspend fun getKelasWithAllSiswa(namaKelas: String): List<KelasAndSiswa>

    suspend fun getAllKelas(): List<Kelas>

    suspend fun getAllTugas(): List<Tugas>

    suspend fun getAllMapel(): List<MataPelajaran>

    suspend fun getMapelWithAllTugas(): List<MataPelajaranAndTugas>

    suspend fun getSiswaWithAllNilai(): List<SiswaAndNilai>

    suspend fun getNilaiWithAllMapel(): List<MataPelajaranAndNilai>

    suspend fun getStudentTotalByClass(): List<Int>

    suspend fun getNilaiByStudentID(idSiswa: Int): List<Nilai>

    suspend fun getAverageByStudent(): List<Double>

    suspend fun insertSiswa(siswa: Siswa): Long

    suspend fun insertMapel(mapel: MataPelajaran): Long

    suspend fun insertKelas(kelas: Kelas): Long

    suspend fun insertNilai(nilai: Nilai): Long

    suspend fun insertTugas(tugas: Tugas): Long

    suspend fun deleteSiswa(siswa: Siswa): Int

    suspend fun deleteMapel(mapel: MataPelajaran): Int

    suspend fun deleteKelas(kelas: Kelas): Int

    suspend fun deleteTugas(tugas: Tugas): Int

    suspend fun deleteNilai(nilai: Nilai): Int

    suspend fun updateTugas(tugas: Tugas): Int

    suspend fun updateMapel(mapel: MataPelajaran): Int

    suspend fun updateStudent(siswa: Siswa): Int
}