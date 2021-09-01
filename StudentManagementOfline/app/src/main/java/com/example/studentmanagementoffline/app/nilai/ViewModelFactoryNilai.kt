package com.example.studentmanagementoffline.app.nilai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagementoffline.domain.usecase.NilaiUseCase

class ViewModelFactoryNilai(private val nilaiUseCase: NilaiUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelNilai(nilaiUseCase) as T
    }
}