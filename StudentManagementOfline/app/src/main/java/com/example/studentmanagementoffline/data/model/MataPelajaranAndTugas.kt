package com.example.studentmanagementoffline.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class MataPelajaranAndTugas(
    @Embedded val mapel: MataPelajaran,
    @Relation(
        parentColumn = "mapelID",
        entityColumn = "mapel_id"
    )
    val tugas: List<Tugas>
)
