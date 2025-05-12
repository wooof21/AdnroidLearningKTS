package com.adnroidlearningkts.dependencyinjection.room.model.repository

import androidx.lifecycle.LiveData
import com.adnroidlearningkts.dependencyinjection.room.model.room.HiltContactDAO
import com.adnroidlearningkts.dependencyinjection.room.model.room.HiltContact
import javax.inject.Inject

class HiltContactRepo @Inject constructor(private val dao: HiltContactDAO) {

    // auto updates the UI when the data changes
    val allContacts: LiveData<List<HiltContact>> = dao.getAllContacts()

    suspend fun insert(contact: HiltContact) = dao.insertContact(contact)

    suspend fun delete(contact: HiltContact) = dao.deleteContact(contact)
}