package com.adnroidlearningkts.retrofit.movieapp.model.apiinterface

import com.adnroidlearningkts.retrofit.movieapp.model.pojo.MovieResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit cannot directly return LiveData - LiveData<Response<T>>
 *
 * When calling this API in main thread, use the suspend fun
 */
interface MovieInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieResult>
}