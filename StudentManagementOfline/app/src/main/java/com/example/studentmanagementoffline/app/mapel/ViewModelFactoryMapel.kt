package com.example.studentmanagementoffline.app.mapel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagementoffline.domain.usecase.MapelUseCase

class ViewModelFactoryMapel(private val mapelUseCase: MapelUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelMapel(mapelUseCase) as T
    }
}