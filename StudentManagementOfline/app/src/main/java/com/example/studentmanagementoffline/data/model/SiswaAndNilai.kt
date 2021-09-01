package com.example.studentmanagementoffline.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class SiswaAndNilai(
    @Embedded val siswa: Siswa,
    @Relation(
        parentColumn = "id_siswa",
        entityColumn = "id_siswa"
    )
    val nilai: List<Nilai>
)
