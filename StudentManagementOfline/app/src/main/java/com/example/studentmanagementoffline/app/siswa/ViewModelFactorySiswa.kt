package com.example.studentmanagementoffline.app.siswa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagementoffline.domain.usecase.SiswaUseCase

class ViewModelFactorySiswa(private val siswaUseCase: SiswaUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelSiswa(siswaUseCase) as T
    }
}