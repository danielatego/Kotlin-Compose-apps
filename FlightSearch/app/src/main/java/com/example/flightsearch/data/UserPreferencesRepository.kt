package com.example.flightsearch.data

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object{
        val LAST_SEARCH = stringPreferencesKey("last_seached_flight")
        const val TAG = "UserPreferencesRepo"
    }
    val lastSearchedFlight: Flow<String> = dataStore.data
        .catch {
            if(it is IOException){
                Log.e(TAG,"Error reading preferences.",it)
                emit(emptyPreferences())
            }else{
                throw it
            }
        }.map { preference->
            preference[LAST_SEARCH] ?: ""
        }
    suspend fun saveLastSearchedItem(lastSearchedItem:String){
        dataStore.edit {preferences->
            preferences[LAST_SEARCH]=lastSearchedItem
        }
    }
}