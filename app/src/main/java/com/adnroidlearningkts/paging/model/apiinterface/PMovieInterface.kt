package com.adnroidlearningkts.paging.model.apiinterface

import com.adnroidlearningkts.paging.model.pojo.PMovieResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PMovieInterface {

    @GET("movie/popular")
    suspend fun getPopularMoviesByPaging(@Query("page") page: Int): Response<PMovieResult>
}