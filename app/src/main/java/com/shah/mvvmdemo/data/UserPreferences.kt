package com.shah.mvvmdemo

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
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

    companion object {
        private val keyAuth = preferencesKey<String>("keyAuth")
    }
}