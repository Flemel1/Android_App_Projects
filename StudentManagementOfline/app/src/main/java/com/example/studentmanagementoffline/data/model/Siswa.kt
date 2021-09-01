package com.example.studentmanagementoffline.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Siswa(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_siswa")
    val idSiswa : Int,
    @ColumnInfo(name = "nama_siswa")
    val namaSiswa : String,
    @ColumnInfo(name = "tanggal_lahir")
    val tanggalLahir : String,
    @ColumnInfo(name = "nama_kelas")
    val namaKelas : String
    )
