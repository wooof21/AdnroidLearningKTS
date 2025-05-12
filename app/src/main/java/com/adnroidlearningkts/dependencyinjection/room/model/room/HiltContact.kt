package com.adnroidlearningkts.dependencyinjection.room.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hilt_contacts")
data class HiltContact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contact_id")
    val id: Int = 0,
    @ColumnInfo(name = "contact_name")
    val name: String,
    @ColumnInfo(name = "contact_phone")
    val phone: String
)