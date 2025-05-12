package com.adnroidlearningkts.mvvm.roomdb.notetaking.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class Note(
    @ColumnInfo("note_id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("note_title")
    var title: String,
    @ColumnInfo("note_body")
    var content: String
): Parcelable

/**
 * Parcelable: an interface used for serializing objects. This serialization allows objects to be efficiently passed
 *  from one activity to another
 *
 * use it with kotlin-parcelize plugin, by annotating the data class with @Parcelize
 * the plugin automatically generates the necessary Parcelable implementation
 *
 * @Parcelize requires all serialized properties to be declared in the primary constructor
 * https://developer.android.com/kotlin/parcelize
 */