package com.example.studentmanagementoffline.app.mapel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.studentmanagementoffline.data.model.MataPelajaran
import com.example.studentmanagementoffline.domain.usecase.MapelUseCase

class ViewModelMapel(private val mapelUseCase: MapelUseCase) : ViewModel() {
    fun getAllMapel(): LiveData<List<MataPelajaran>> = liveData {
        emit(mapelUseCase.getAllMapel())
    }
    fun insertMapel(mapel: MataPelajaran): LiveData<Long> = liveData {
        emit(mapelUseCase.insertMapel(mapel))
    }
    fun deleteMapel(mapel: MataPelajaran): LiveData<Int> = liveData {
        emit(mapelUseCase.deleteMapel(mapel))
    }
    fun updateMapel(mapel: MataPelajaran): LiveData<Int> = liveData {
        emit(mapelUseCase.updateMapel(mapel))
    }
}