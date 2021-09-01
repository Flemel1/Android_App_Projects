package com.example.studentmanagementoffline.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Mata_Pelajaran")
data class MataPelajaran(
    @PrimaryKey
    val mapelID : String,
    @ColumnInfo(name = "nama_mapel")
    val namaMapel : String
)
