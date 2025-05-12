package com.adnroidlearningkts.mvvm.roomdb.notetaking.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note_table ORDER BY note_id DESC")
    fun getAll(): LiveData<List<Note>>

    //: is used in Room's @Query annotation to denote a parameter placeholder.
    @Query("SELECT * FROM note_table WHERE note_title LIKE :query OR note_body LIKE :query")
    fun search(query: String?): LiveData<List<Note>>
}