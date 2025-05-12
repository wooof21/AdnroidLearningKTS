package com.adnroidlearningkts.dependencyinjection.retrofit.model.repository

import com.adnroidlearningkts.dependencyinjection.retrofit.model.config.PostsInterface
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class PostRepo @Inject constructor(private val api: PostsInterface) {

    suspend fun getPosts() = api.getPosts()
}