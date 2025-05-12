package com.adnroidlearningkts.mvvm.roomdb.contactmanager.model

import androidx.lifecycle.LiveData


/**
 * Repository: the bridge between ViewModel and Data Source
 *
 *  - provide a clean API for ViewModel to interact with the underlying RoomDB through the ContactDAO
 *  - encapsulate the logic related to data operations, allowing the ViewModel to focus on handling \
 *      UI related logic
 *  - call all functions in DAO
 */
class ContactRepository(private val contactDao: ContactDAO) {

    val contacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    suspend fun insert(contact: Contact): Long {
        return contactDao.insertContact(contact)
    }

    suspend fun delete(contact: Contact) {
        return contactDao.deleteContact(contact)
    }

    suspend fun update(contact: Contact) {
        return contactDao.updateContact(contact)
    }

    suspend fun deleteAll() {
        return contactDao.deleteAllContacts()
    }

}