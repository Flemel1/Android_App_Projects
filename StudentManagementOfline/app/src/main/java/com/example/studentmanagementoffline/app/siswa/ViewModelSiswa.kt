package com.example.studentmanagementoffline.app.siswa

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.studentmanagementoffline.data.model.KelasAndSiswa
import com.example.studentmanagementoffline.data.model.Siswa
import com.example.studentmanagementoffline.data.model.SiswaAndNilai
import com.example.studentmanagementoffline.domain.usecase.SiswaUseCase

class ViewModelSiswa(val siswaUseCase: SiswaUseCase) : ViewModel() {

    fun getKelasWithAllSiswa(namaKelas: String): LiveData<List<KelasAndSiswa>> =
        liveData {
            emit(siswaUseCase.getKelasWithAllSiswa(namaKelas))
        }

    fun getSiswaWithAllNilai(): LiveData<List<SiswaAndNilai>> = liveData {
        emit(siswaUseCase.getSiswaWithAllNilai())
    }

    fun insertSiswa(siswa: Siswa): LiveData<Long> = liveData {
        emit(siswaUseCase.insertSiswa(siswa))
    }

    fun deleteSiswa(siswa: Siswa): LiveData<Int> = liveData {
        emit(siswaUseCase.deleteSiswa(siswa))
    }

    fun updateStudent(siswa: Siswa): LiveData<Int> = liveData {
        emit(siswaUseCase.updateStudent(siswa))
    }

}