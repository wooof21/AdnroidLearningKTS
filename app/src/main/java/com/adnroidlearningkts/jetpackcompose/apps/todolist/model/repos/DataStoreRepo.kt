package com.adnroidlearningkts.jetpackcompose.apps.todolist.model.repos

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Constants.DATA_STORE_PREFERENCE_KEY_SORT
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Constants.DATA_STORE_PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * DataStore to save the last sort option, when relaunch the app, the sort state is persist
 */
val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_PREFERENCE_NAME)

//the DataStoreRepo will be provided to a ViewModel class and scoped to the ViewModel lifecycle
@ViewModelScoped
class DataStoreRepo @Inject constructor(
    @ApplicationContext private val context: Context
){

    private object PreferenceKeys {
        val sortKey = stringPreferencesKey(name = DATA_STORE_PREFERENCE_KEY_SORT)
    }

    private val dataStore = context.datastore

    suspend fun persistSortState(priority: TaskPriority) {
        dataStore.edit {preference ->
            preference[PreferenceKeys.sortKey] = priority.name
        }
    }

    /**
     * Flow is a Kotlin coroutine concept for handling streams of data asynchronously.
     *
     * dataStore.data: This property of DataStore returns a Flow<Preferences> that emits the
     * latest Preferences object whenever the data changes.
     */
    val readSortState: Flow<String> = dataStore.data
        .catch {exception ->
            if(exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {prefs ->
            val sortState = prefs[PreferenceKeys.sortKey] ?: TaskPriority.NONE.name
            sortState
        }

}