package com.adnroidlearningkts.mvvm.roomdb.notetaking.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adnroidlearningkts.mvvm.roomdb.notetaking.model.NoteRepo

class NoteViewModelFactory(private val app: Application,
                           private val repo: NoteRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(app, repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}