package com.adnroidlearningkts.retrofit

import com.google.gson.annotations.SerializedName

/**
 * 1. Data Class
 *
 * use @SerializedName when JSON property name is different than POJO variable name
 * the provided name is mapped to the key in JSON
 *  - dont need the annotation when name matches
 */
data class Album(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("userId")
    private val userId: Int,
    @SerializedName("title")
    private val title: String
)
