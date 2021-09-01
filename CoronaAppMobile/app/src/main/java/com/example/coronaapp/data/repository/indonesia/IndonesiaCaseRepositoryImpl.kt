package com.example.coronaapp.data.repository.indonesia

import android.util.Log
import com.example.coronaapp.data.entities.CoronaIndonesiaList
import com.example.coronaapp.data.entities.ProvinsiList
import com.example.coronaapp.data.repository.indonesia.datasource.IndonesiaRemoteDataSource
import com.example.coronaapp.domain.repository.IndonesiaCaseRepository

class IndonesiaCaseRepositoryImpl(
    private val indonesiaRemoteDataSource: IndonesiaRemoteDataSource
    ) : IndonesiaCaseRepository {

    val TAG = "IndonesiaCaseRepository"

    override suspend fun getAllIndonesiaCase(): CoronaIndonesiaList = getAllIndonesiaCaseAPI()

    override suspend fun getAllProvinceCase(): ProvinsiList = getAllProvinceCaseAPI()

    suspend fun getAllIndonesiaCaseAPI(): CoronaIndonesiaList {
        var allIndonesiaCase = CoronaIndonesiaList()
        try {
            val response = indonesiaRemoteDataSource.getAllIndonesiaCase()
            val body = response.body()
            if (body != null) {
                allIndonesiaCase = body
                Log.d(TAG, "list size : ${allIndonesiaCase.size}")
            }
        }
        catch (ex : Exception) {
            Log.i(TAG, ex.message.toString())
        }
        return allIndonesiaCase
    }

    suspend fun getAllProvinceCaseAPI(): ProvinsiList {
        lateinit var allProvinceCase : ProvinsiList
        try {
            val response = indonesiaRemoteDataSource.getAllProvinceCase()
            val body = response.body()
            if (body != null) {
                allProvinceCase = body
            }
        }
        catch (ex : Exception) {
            Log.i(TAG, ex.message.toString())
        }
        return allProvinceCase
    }
}