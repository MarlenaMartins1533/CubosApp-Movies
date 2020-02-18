package com.marlena.cubosapp_movies.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.marlena.cubosapp_movies.model.domain.Movie

data class SearchResponse(

    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("total_results")
    @Expose
    val total_results: Int,

    @SerializedName("total_pages")
    @Expose
    val total_pages: Int,

    @SerializedName("results")
    @Expose
    val results: List<Movie>
)