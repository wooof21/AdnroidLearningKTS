package com.adnroidlearningkts.dependencyinjection.room.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//DAO: define method access the RoomDB
@Dao
interface HiltContactDAO {

    //onConflict: when the inserted id field ia already exist, replace the existing record with new contact
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: HiltContact)

    @Delete
    suspend fun deleteContact(contact: HiltContact)

    @Query("SELECT * FROM hilt_contacts")
    fun getAllContacts(): LiveData<List<HiltContact>>
}