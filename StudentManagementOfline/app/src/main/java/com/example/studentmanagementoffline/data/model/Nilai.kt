package com.example.studentmanagementoffline.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Nilai(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val nilai : Int,
    @ColumnInfo(name = "id_siswa")
    val idSiswa : Int,
    @ColumnInfo(name = "mapel_id")
    val mapelID : String,
    @ColumnInfo(name = "tugas_id")
    val tugasID : Int
)
