package com.example.studentmanagementoffline.app.tugas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.studentmanagementoffline.data.model.MataPelajaranAndTugas
import com.example.studentmanagementoffline.data.model.Tugas
import com.example.studentmanagementoffline.domain.usecase.TugasUseCase

class ViewModelTugas(private val tugasUseCase: TugasUseCase) : ViewModel() {
    fun getMapelWithAllTugas(): LiveData<List<MataPelajaranAndTugas>> = liveData() {
        emit(tugasUseCase.getMapelWithAllTugas())
    }
    fun getAllTugas(): LiveData<List<Tugas>> = liveData {
        emit(tugasUseCase.getAllTugas())
    }
    fun insertTugas(tugas: Tugas): LiveData<Long> = liveData {
        emit(tugasUseCase.insertTugas(tugas))
    }
    fun deleteTugas(tugas: Tugas): LiveData<Int> = liveData {
        emit(tugasUseCase.deleteTugas(tugas))
    }
    fun updateTugas(tugas: Tugas): LiveData<Int> = liveData {
        emit(tugasUseCase.updateTugas(tugas))
    }
}