package com.adnroidlearningkts.retrofit.movieapp.model.config

import com.adnroidlearningkts.retrofit.movieapp.model.apiinterface.MovieInterface

object ApiConfig {

    val movieApi: MovieInterface = RetrofitConfig.instance.create(MovieInterface::class.java)
}