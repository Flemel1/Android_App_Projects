package com.example.studentmanagementoffline.data.repository

import com.example.studentmanagementoffline.data.model.*
import com.example.studentmanagementoffline.data.repository.datasource.*
import com.example.studentmanagementoffline.domain.repository.StudentManagementRepo

class StudentManagementRepoImpl(
    private val kelasDataSource: KelasDataSource,
    private val nilaiDataSource: NilaiDataSource,
    private val siswaDataSource: SiswaDataSource,
    private val tugasDataSource: TugasDataSource,
    private val mapelDataSource: MapelDataSource
) : StudentManagementRepo {
    override suspend fun getKelasWithAllSiswa(namaKelas: String): List<KelasAndSiswa> =
        siswaDataSource.getKelasWithAllSiswa(namaKelas)

    override suspend fun getAllKelas(): List<Kelas> = kelasDataSource.getAllKelas()

    override suspend fun getAllTugas(): List<Tugas> = tugasDataSource.getAllTugas()

    override suspend fun getAllMapel(): List<MataPelajaran> = mapelDataSource.getAllMapel()

    override suspend fun getMapelWithAllTugas(): List<MataPelajaranAndTugas> =
        tugasDataSource.getMapelWithAllTugas()

    override suspend fun getSiswaWithAllNilai(): List<SiswaAndNilai> =
        siswaDataSource.getSiswaWithAllNilai()

    override suspend fun getNilaiWithAllMapel(): List<MataPelajaranAndNilai> =
        nilaiDataSource.getNilaiWithAllMapel()

    override suspend fun getStudentTotalByClass(): List<Int> =
        kelasDataSource.getStudentTotalByClass()

    override suspend fun getNilaiByStudentID(idSiswa: Int): List<Nilai> =
        nilaiDataSource.getNilaiByStudentID(idSiswa)

    override suspend fun getAverageByStudent(): List<Double> = nilaiDataSource.getAverageByStudent()

    override suspend fun insertSiswa(siswa: Siswa): Long = siswaDataSource.insertSiswa(siswa)

    override suspend fun insertMapel(mapel: MataPelajaran): Long =
        mapelDataSource.insertMapel(mapel)

    override suspend fun insertKelas(kelas: Kelas): Long = kelasDataSource.insertKelas(kelas)

    override suspend fun insertNilai(nilai: Nilai): Long = nilaiDataSource.insertNilai(nilai)

    override suspend fun insertTugas(tugas: Tugas): Long = tugasDataSource.insertTugas(tugas)

    override suspend fun deleteSiswa(siswa: Siswa): Int = siswaDataSource.deleteSiswa(siswa)

    override suspend fun deleteMapel(mapel: MataPelajaran): Int = mapelDataSource.deleteMapel(mapel)

    override suspend fun deleteKelas(kelas: Kelas): Int = kelasDataSource.deleteKelas(kelas)

    override suspend fun deleteTugas(tugas: Tugas): Int = tugasDataSource.deleteTugas(tugas)

    override suspend fun deleteNilai(nilai: Nilai): Int = nilaiDataSource.deleteNilai(nilai)

    override suspend fun updateTugas(tugas: Tugas): Int = tugasDataSource.updateTugas(tugas)

    override suspend fun updateMapel(mapel: MataPelajaran): Int = mapelDataSource.updateMapel(mapel)

    override suspend fun updateStudent(siswa: Siswa): Int = siswaDataSource.updateStudent(siswa)
}