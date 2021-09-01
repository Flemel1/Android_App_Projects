package com.example.studentmanagementoffline.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Kelas(
    @PrimaryKey
    @ColumnInfo(name = "nama_kelas")
    val namaKelas : String
)
