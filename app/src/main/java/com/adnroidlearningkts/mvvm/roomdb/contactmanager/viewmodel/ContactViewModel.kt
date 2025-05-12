package com.adnroidlearningkts.mvvm.roomdb.contactmanager.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.model.Contact
import com.adnroidlearningkts.mvvm.roomdb.contactmanager.model.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel: store and manage UI-related data
 * separating the UI-related logic from UI controller(Activity/Fragment)
 *
 * Observable interface: allow the observers to subscribe and receive notifications
 * about changes in the observed object's state
 *
 * Error: Caused by: java.lang.RuntimeException:
 *  Cannot create an instance of class com.adnroidlearningkts.mvvm.roomdb.viewmodel.ContactViewModel
 *
 * When a ViewModel class has a `constructor` with parameters, cannot use the default constructor
 * that the ViewModel framework provide
 *  *** use ViewModel factory to pass the required parameters when creating an instance of ViewModel
 */
class ContactViewModel(private val repo: ContactRepository): ViewModel(), Observable {

    val contacts = repo.contacts
    private var isUpdateOrDelete = false
    private lateinit var contactToUpdateOrDelete: Contact

    /**
     * DataBinding with LiveData
     *
     * inputName & inputEmail: declare a read&write property and initialized with an instance of MutableLiveData
     * of type nullable String
     */
    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val inputEmail = MutableLiveData<String?>()
    @Bindable
    val saveOrUpdateBtnText = MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteBtnText = MutableLiveData<String>()

    init {
        /**
         * Init:
         *  isUpdateOrDelete = false ->
         *      saveOrUpdateBtnText.value = "Save"
         *      clearAllOrDeleteBtnText.value = "Clear All"
         */
        saveOrUpdateBtnText.value = "Save"
        clearAllOrDeleteBtnText.value = "Clear All"
    }

    /**
     * Using Coroutines to launch a new Coroutine of inserting a new contact within the scope of ViewModel
     */
    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(contact)

        //resetting the Buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateBtnText.value = "Save"
        clearAllOrDeleteBtnText.value = "Clear All"
    }

    fun update(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(contact)

        //resetting the Buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateBtnText.value = "Save"
        clearAllOrDeleteBtnText.value = "Clear All"
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteAll()
    }

    fun detectSaveOrUpdate() {
        if(isUpdateOrDelete) {
            /**
             * update:
             *
             * !! -> not-null assertion operator
             * It tells the compiler, "I am sure that the value of inputName.value is not null at this point.
             * Treat it as if it's not nullable."
             *
             * If inputName.value actually is null when this line is executed,
             * a NullPointerException will be thrown.
             * This is why the !! operator should be used cautiously.
             *
             */
            contactToUpdateOrDelete.name = inputName.value!!
            contactToUpdateOrDelete.email = inputEmail.value!!
            update(contactToUpdateOrDelete)
        } else {
            //insert a new contact
            val name = inputName.value!!
            val email = inputEmail.value!!
            /**
             * 0 -> when Room sees 0 for an integer primary key that is marked with autoGenerate = true,
             *  it interprets this as a signal that it should generate the unique ID for this row instead of using the provided 0.
             */
            insert(Contact(0, name, email))

            //reset name and email
            inputName.value = null
            inputEmail.value = null
        }
    }

    fun detectClearAllOrDelete() {
        if(isUpdateOrDelete) {
            delete(contactToUpdateOrDelete)
        } else {
            deleteAll()
        }
    }

    fun onItemClicked(contact: Contact) {
        inputName.value = contact.name
        inputEmail.value = contact.email
        /**
         * When click an item,
         * set flag `isUpdateOrDelete` to true to change the Button text
         *  -> set Buttons to "Update" & "Delete"
         */
        isUpdateOrDelete = true
        contactToUpdateOrDelete = contact
        saveOrUpdateBtnText.value = "Update"
        clearAllOrDeleteBtnText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}