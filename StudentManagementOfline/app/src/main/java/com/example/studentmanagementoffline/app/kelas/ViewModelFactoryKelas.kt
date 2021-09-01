package com.example.studentmanagementoffline.app.kelas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagementoffline.domain.usecase.KelasUseCase

class ViewModelFactoryKelas(private val kelasUseCase: KelasUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelKelas::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelKelas(kelasUseCase) as T
        }
        throw IllegalArgumentException("Uknown View Model Class")
    }
}