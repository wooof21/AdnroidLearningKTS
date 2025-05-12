package com.adnroidlearningkts.dependencyinjection.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnroidlearningkts.dependencyinjection.room.model.repository.HiltContactRepo
import com.adnroidlearningkts.dependencyinjection.room.model.room.HiltContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiltContactViewModel @Inject constructor(private val repo: HiltContactRepo): ViewModel() {

    val allContacts = repo.allContacts

    /**
     * explicitly define the CoroutineDispatcher
     *
     * viewModelScope by default use the Dispatchers.Main thread
     */
    fun insertContact(contact: HiltContact) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(contact)
    }

    fun deleteContact(contact: HiltContact) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(contact)
    }
}