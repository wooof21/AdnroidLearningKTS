package com.adnroidlearningkts.mvvm.roomdb.notetaking.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.Note
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.NoteRepo
import kotlinx.coroutines.launch

/**
 * AndroidViewModel -> expect Application in primary constructor
 */
class NoteViewModel(
    private val app: Application,
    private val noteRepo: NoteRepo): AndroidViewModel(app) {

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepo.insertNote(note)
    }

    fun updateNode(note: Note) = viewModelScope.launch {
        noteRepo.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepo.deleteNote(note)
    }

    fun getAllNotes() = noteRepo.getAllNotes()

    fun searchNote(query: String?) = noteRepo.searchNote(query)
}