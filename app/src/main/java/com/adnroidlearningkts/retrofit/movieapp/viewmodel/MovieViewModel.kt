package com.adnroidlearningkts.retrofit.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.adnroidlearningkts.retrofit.movieapp.model.repository.MovieRepo

class MovieViewModel(private val app: Application, private val repo: MovieRepo) : AndroidViewModel(app) {

    suspend fun getPopularMovies(apiKey: String) = repo.getPopularMovies(apiKey)
}