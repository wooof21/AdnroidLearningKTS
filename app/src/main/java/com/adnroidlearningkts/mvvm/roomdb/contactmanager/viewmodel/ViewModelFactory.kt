package com.adnroidlearningkts.mvvm.roomdb.contactmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.model.ContactRepository


/**
 * When a ViewModel class has a `constructor` with parameters, cannot use the default constructor
 *  * that the ViewModel framework provide
 *  *  *** use ViewModel factory to pass the required parameters when creating an instance of ViewModel
 */
class ViewModelFactory(private val repo: ContactRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //check modelClass is compatible with ContactViewModel class
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}