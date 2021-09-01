package com.example.studentmanagementoffline.app.tugas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagementoffline.domain.usecase.TugasUseCase

class ViewModelFactoryTugas(private val tugasUseCase: TugasUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelTugas(tugasUseCase) as T
    }
}