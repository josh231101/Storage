package com.example.misdatos.viewmodels

import android.content.Context
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesViewModel(val context: Context): ViewModel() {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val NAME = stringPreferencesKey("name")
        val AGE = intPreferencesKey("age")
        val HEIGHT = floatPreferencesKey("height")
        val WEIGHT = floatPreferencesKey("weight")
        val HOBBY = stringPreferencesKey("hobby")
    }

    //getName() asincrono
    val name: Flow<String> = context.dataStore.data
                            .map { preferences ->
                                preferences[NAME] ?: ""
                            }

    //getAge() asincrono
    val age: Flow<Int> = context.dataStore.data
                            .map { preferences ->
                                preferences[AGE] ?: 0
                            }

    //getHeight() asincrono
    val height: Flow<Any> = context.dataStore.data
                            .map { preferences ->
                                preferences[HEIGHT] ?: 0.0
                            }

    //getWeight() asincrono
    val weight: Flow<Any> = context.dataStore.data
                            .map { preferences ->
                                preferences[WEIGHT] ?: 0.0
                            }

    //getHobby() asincrono
    val hobby: Flow<String> = context.dataStore.data
                            .map { preferences ->
                                preferences[HOBBY] ?: ""
                            }

    //setName & setAge
    suspend fun setNameAndAge(name: String, age: Int, height: Float, weight: Float, hobby: String) {
        context.dataStore.edit { settings ->
            settings[NAME] = name
            settings[AGE] = age
            settings[HEIGHT] = height
            settings[WEIGHT] = weight
            settings[HOBBY] = hobby
        }
    }
}