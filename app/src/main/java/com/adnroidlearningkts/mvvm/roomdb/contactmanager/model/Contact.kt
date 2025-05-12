package com.adnroidlearningkts.mvvm.roomdb.contactmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Entity: used to indicate that a data class represents a table in RoomDB
 *
 * if `tableName` is not provided, room will use the class name as table name
 *
 * `ColumnInfo`: used to specify and map the property name to column name in databse
 *      if they are different
 */
@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_id")
    var id: Int,

    @ColumnInfo(name = "contact_name")
    var name: String,

    @ColumnInfo(name = "contact_email")
    var email: String)
