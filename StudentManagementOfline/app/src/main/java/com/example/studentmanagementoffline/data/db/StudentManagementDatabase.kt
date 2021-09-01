package com.example.studentmanagementoffline.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studentmanagementoffline.data.model.*

@Database(
    entities = [Kelas::class, MataPelajaran::class,
        Nilai::class, Siswa::class, Tugas::class],
    version = 1
)
abstract class StudentManagementDatabase : RoomDatabase() {
    abstract fun studentManagementDao(): StudentManagementDao
}