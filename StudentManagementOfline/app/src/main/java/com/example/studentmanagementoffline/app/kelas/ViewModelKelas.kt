package com.example.studentmanagementoffline.app.kelas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.studentmanagementoffline.data.model.Kelas
import com.example.studentmanagementoffline.data.model.Nilai
import com.example.studentmanagementoffline.domain.usecase.KelasUseCase

class ViewModelKelas(private val kelasUseCase: KelasUseCase) : ViewModel() {
    fun getAllKelas(): LiveData<List<Kelas>> = liveData {
        emit(kelasUseCase.getAllKelas())
    }
    fun insertKelas(kelas: Kelas): LiveData<Long> = liveData {
        emit(kelasUseCase.insertKelas(kelas))
    }
    fun deleteKelas(kelas: Kelas): LiveData<Int> = liveData {
        emit(kelasUseCase.deleteKelas(kelas))
    }

    fun getStudentTotalByClass(): LiveData<List<Int>> = liveData {
        emit(kelasUseCase.getStudentTotalByClass())
    }
}