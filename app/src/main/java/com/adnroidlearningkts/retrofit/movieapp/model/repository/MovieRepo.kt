package com.adnroidlearningkts.retrofit.movieapp.model.repository

import com.adnroidlearningkts.retrofit.movieapp.model.config.ApiConfig

class MovieRepo() {

    suspend fun getPopularMovies(apiKey: String) = ApiConfig.movieApi.getPopularMovies(apiKey)
}