package com.adnroidlearningkts.mvvm.roomdb.contactmanager.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


/**
 * Data Access Object: a design pattern that provides an abstract interface to database, it allows you
 * to interact with database by defining methods to CRUD operations on the data
 */
@Dao
interface ContactDAO {

    /**
     * @Insert
     *      used by RoomDB library to identify that the associated method is used for inserting data
     *      into database
     *      * return:
     *          -1 : insertion failed
     *          long: the auto generate ID for the inserted record
     *
     * @Insert, @Update, @Delete
     * Room uses it to generate necessary code when use these annotations
     *
     * @Query: define custom SQL query for DB operation
     */
    @Insert
    suspend fun insertContact(contact: Contact): Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAllContacts()

    /**
     * LiveData:
     *      * allow data to be observed for changes
     *      * common pattern when dealing with RoomDB queries
     *      * automatically update the UI whenever the underlying data changes
     *
     */
    @Query("SELECT * FROM contacts_table")
    fun getAllContacts(): LiveData<List<Contact>>
}