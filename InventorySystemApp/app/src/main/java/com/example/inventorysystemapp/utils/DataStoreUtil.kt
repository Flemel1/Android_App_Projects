package com.example.inventorysystemapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreUtil(context: Context) {

    private val dataStore : DataStore<Preferences> = context.createDataStore("login_record")

    val loginStatusFlow: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[IS_LOGIN] ?: false
        }

    val userStatusFlow: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[STATUS_USER] ?: "nothing"
        }

    companion object {
        val IS_LOGIN = booleanPreferencesKey("login_status")
        val STATUS_USER = stringPreferencesKey("status_user")
    }

    suspend fun setStatusUser(status: String) {
        dataStore.edit { preferences ->
            preferences[STATUS_USER] = status
        }
    }

    suspend fun setStatusLogin(isLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGIN] = isLogin
        }
    }
}