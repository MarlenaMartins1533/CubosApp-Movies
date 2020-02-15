package com.marlena.cubosapp_movies.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.marlena.cubosapp_movies.model.domain.Movie

data class MovieResponse(

    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,

    @SerializedName("results")
    @Expose
    val results: List<Movie>
)