package com.shah.mvvmdemo.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences (private val context: Context) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore("myDataStore")
    }

    val authToken : Flow<String?>
    get() = dataStore.data.map { preferences ->
        preferences[keyAuth]
    }

    suspend fun saveAuthToken (authToken: String) {
        dataStore.edit { preferences ->
            preferences[keyAuth] = authToken
        }
    }

    suspend fun clear() {
        dataStore.edit {
            it.clear()
        }
    }
    companion object {
        private val keyAuth = preferencesKey<String>("keyAuth")
    }
}