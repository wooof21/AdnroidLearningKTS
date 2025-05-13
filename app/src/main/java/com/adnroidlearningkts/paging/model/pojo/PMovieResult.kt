package com.adnroidlearningkts.paging.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PMovieResult(
    val page: Int,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int,
    @SerializedName("results")
    @Expose
    val movies: List<PMovie>) {
}