package com.adnroidlearningkts.retrofit.movieapp.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Customize the serialization and deserialization of JSON data when working
 * with JSON as the JSON converter
 * these annotations allow you to control the mapping between the Kotlin or Java Objects
 * and JSON keys in the API response
 *
 * SerializedName: used to specify the name of the JSON key and correspond to a particular
 * field or property in the Kotlin or Java class
 * - useful when variable name in the class differ from JSON key
 *
 * Expose: used to specify which fields should be included or excluded when JSON serialize
 * and deserialize
 * - by default, JSON includes all fields
 * - used for security or data privacy reasons to prevent certain fields from being included
 */
data class MovieResult(
    val page: Int,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int,
    @SerializedName("results")
    @Expose
    val movies: List<Movie>
)
