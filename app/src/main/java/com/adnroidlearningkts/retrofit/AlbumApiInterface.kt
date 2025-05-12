package com.adnroidlearningkts.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AlbumApiInterface {

    @GET("/albums")
//    suspend fun getAllAlbums(): Response<Albums>
    suspend fun getAllAlbums(): Response<List<Album>>

    /**
     * add query params:
     * /albums?userId=8
     */
    @GET("/albums")
    suspend fun getAlbumsByUserId(@Query("userId") userId: Int): Response<List<Album>>

    /**
     * Path params:
     *
     */
    @GET("/albums/{id}")
    suspend fun getAlbumsById(@Path("id") id: Int): Response<Album>

}