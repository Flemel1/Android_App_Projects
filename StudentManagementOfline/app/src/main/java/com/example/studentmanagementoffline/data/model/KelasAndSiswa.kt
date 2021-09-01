package com.example.studentmanagementoffline.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class KelasAndSiswa(
    @Embedded val kelas : Kelas,
    @Relation(
        parentColumn = "nama_kelas",
        entityColumn = "nama_kelas"
    )
    val siswa : List<Siswa>
)
