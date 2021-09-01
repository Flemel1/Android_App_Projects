package com.example.studentmanagementoffline.app.nilai

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.studentmanagementoffline.data.model.Nilai
import com.example.studentmanagementoffline.data.model.MataPelajaranAndNilai
import com.example.studentmanagementoffline.domain.usecase.NilaiUseCase

class ViewModelNilai(private val nilaiUseCase: NilaiUseCase) : ViewModel() {
    fun getNilaiWithAllMapel(): LiveData<List<MataPelajaranAndNilai>> = liveData {
        emit(nilaiUseCase.getNilaiWithAllMapel())
    }
    fun insertNilai(nilai: Nilai): LiveData<Long> = liveData {
        emit(nilaiUseCase.insertNilai(nilai))
    }
    fun deleteNilai(nilai: Nilai): LiveData<Int> = liveData {
        emit(nilaiUseCase.deleteNilai(nilai))
    }

    fun getNilaiByStudentID(idSiswa: Int): LiveData<List<Nilai>> = liveData {
        emit(nilaiUseCase.getNilaiByStudentID(idSiswa))
    }

    fun getAverageByStudent(): LiveData<List<Double>> = liveData {
        emit(nilaiUseCase.getAverageByStudent())
    }
}