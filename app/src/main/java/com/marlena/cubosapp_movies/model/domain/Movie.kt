package com.marlena.cubosapp_movies.model.domain

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("title")
    val title: String,

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,

    @SerializedName("overview")
    val overview: String = "unknown",

    @SerializedName("poster_path")
    val poster_path: String
)