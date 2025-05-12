package com.adnroidlearningkts.mvvm.roomdb.notetaking.model

class NoteRepo(private val noteDB: NoteDatabase) {

    suspend fun insertNote(note: Note) = noteDB.getNoteDao().insert(note)

    suspend fun updateNote(note: Note) = noteDB.getNoteDao().update(note)

    suspend fun deleteNote(note: Note) = noteDB.getNoteDao().delete(note)

    fun getAllNotes() = noteDB.getNoteDao().getAll()

    fun searchNote(query: String?) = noteDB.getNoteDao().search(query)
}