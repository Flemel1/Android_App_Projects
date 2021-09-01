package com.example.studentmanagementoffline.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tugas(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tugas_id")
    val id : Int,
    @ColumnInfo(name = "nama_tugas")
    val namaTugas: String,
    @ColumnInfo(name = "mapel_id")
    val mapelID : String,
    @ColumnInfo(name = "nama_kelas")
    val namaKelas : String,
    @ColumnInfo(name = "deskripsi_tugas")
    val deskripsiTugas : String,
    @ColumnInfo(name = "deadline_tugas")
    val deadlineTugas : String
)
