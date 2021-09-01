package com.example.coronaapp.data.repository.world

import android.util.Log
import com.example.coronaapp.data.entities.CountryList
import com.example.coronaapp.data.entities.Died
import com.example.coronaapp.data.entities.Positif
import com.example.coronaapp.data.entities.Sembuh
import com.example.coronaapp.data.repository.world.datasource.CountryRemoteDataSource
import com.example.coronaapp.domain.repository.WorldCaseRepository

class WorldCaseRepositoryImpl(private val countryRemoteDataSource: CountryRemoteDataSource
    ) : WorldCaseRepository {

    val TAG = "WorldCaseRepository"

    override suspend fun getAllWorldCase(): CountryList {
        lateinit var allWorldCase: CountryList
        try {
            val response = countryRemoteDataSource.getAllWorldCase()
            val body = response.body()
            if (body != null) {
                allWorldCase = body
            }
        } catch (ex: Exception) {
            Log.i(TAG, ex.message.toString())
        }
        return allWorldCase
    }

    override suspend fun getWorldPositiveCase(): Positif {
        lateinit var positif: Positif
        try {
            val response = countryRemoteDataSource.getWorldPositiveCase()
            val body = response.body()
            if (body != null) {
                positif = body
            }
        } catch (ex: Exception) {
            Log.i(TAG, ex.message.toString())
        }
        return positif
    }

    override suspend fun getWorldRecoverCase(): Sembuh {
        lateinit var recovery: Sembuh
        try {
            val response = countryRemoteDataSource.getWorldRecoverCase()
            val body = response.body()
            Log.d("TAG", body.toString())
            if (body != null) {
                recovery = body
            }
        } catch (ex: Exception) {
            Log.i(TAG, ex.message.toString())
        }
        return recovery
    }

    override suspend fun getWorldDiedCase(): Died {
        lateinit var died: Died
        try {
            val response = countryRemoteDataSource.getWorldDiedCase()
            val body = response.body()
            if (body != null) {
                died = body
            }
        } catch (ex: Exception) {
            Log.i(TAG, ex.message.toString())
        }
        return died
    }

}