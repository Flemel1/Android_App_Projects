package com.example.studentmanagementoffline.data.db

import androidx.room.*
import com.example.studentmanagementoffline.data.model.*

@Dao
interface StudentManagementDao {

    //Query SELECT
    @Transaction
    @Query("SELECT * FROM Siswa WHERE nama_kelas = :namaKelas")
    suspend fun getKelasWithAllSiswa(namaKelas: String): List<KelasAndSiswa>

    @Query("SELECT * FROM kelas")
    suspend fun getAllKelas(): List<Kelas>

    @Query("SELECT * FROM tugas")
    suspend fun getAllTugas(): List<Tugas>

    @Query("SELECT * FROM Mata_Pelajaran")
    suspend fun getAllMapel(): List<MataPelajaran>

    @Transaction
    @Query("SELECT * FROM Mata_Pelajaran")
    suspend fun getMapelWithAllTugas(): List<MataPelajaranAndTugas>

    @Transaction
    @Query("SELECT * FROM Siswa")
    suspend fun getSiswaWithAllNilai(): List<SiswaAndNilai>

    @Transaction
    @Query("SELECT * FROM Mata_Pelajaran")
    suspend fun getNilaiWithAllMapel(): List<MataPelajaranAndNilai>

    @Query("SELECT COUNT(id_siswa) FROM Siswa WHERE nama_kelas = nama_kelas GROUP BY nama_kelas")
    suspend fun getStudentTotalByClass(): List<Int>

    @Query("SELECT * FROM Nilai WHERE id_siswa = :idSiswa")
    suspend fun getNilaiByStudentID(idSiswa: Int): List<Nilai>

    @Query("SELECT AVG(nilai) FROM Nilai WHERE id_siswa = id_siswa GROUP BY id_siswa")
    suspend fun getAverageByStudent(): List<Double>

    //Query INSERT return Long for get rowId if insert is success
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSiswa(siswa: Siswa): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMapel(mapel: MataPelajaran): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKelas(kelas: Kelas): Long

    @Insert
    suspend fun insertNilai(nilai: Nilai): Long

    @Insert
    suspend fun insertTugas(tugas: Tugas): Long

    //Query Delete can return an int value indicate the number of rows that were deleted successfully.
    @Delete
    suspend fun deleteSiswa(siswa: Siswa): Int

    @Delete
    suspend fun deleteMapel(mapel: MataPelajaran): Int

    @Delete
    suspend fun deleteKelas(kelas: Kelas): Int

    @Delete
    suspend fun deleteTugas(tugas: Tugas): Int

    @Delete
    suspend fun deleteNilai(nilai: Nilai): Int

    //Query update
    @Update
    suspend fun updateTugas(tugas: Tugas): Int

    @Update
    suspend fun updateMapel(mapel: MataPelajaran): Int

    @Update
    suspend fun updateStudent(siswa: Siswa): Int
}