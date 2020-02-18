package com.marlena.cubosapp_movies.service

import com.marlena.cubosapp_movies.data.Constants
import com.marlena.cubosapp_movies.model.response.GenreResponse
import com.marlena.cubosapp_movies.model.response.MovieResponse
import com.marlena.cubosapp_movies.model.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getMovies(
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1,
        @Query("api_key") appIdMovie: String = Constants.appKeyMovie
    ): Call<MovieResponse>

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String = Constants.appKeyMovie
    ): Call<GenreResponse>

    @GET("search/movie")
    fun getResultSearch(
        @Query("api_key") appIdMovie: String = Constants.appKeyMovie,
        @Query("language") language: String = "pt-BR",
        @Query("query") query: String = "",
        @Query("page") page: Int = 1

    ): Call<SearchResponse>
}
