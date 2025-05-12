package com.adnroidlearningkts.retrofit.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adnroidlearningkts.retrofit.movieapp.model.repository.MovieRepo

class MovieViewModelFactory(private val app: Application,
                            private val repo: MovieRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(app, repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}