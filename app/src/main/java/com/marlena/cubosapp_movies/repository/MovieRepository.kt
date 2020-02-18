package com.marlena.cubosapp_movies.repository

import com.marlena.cubosapp_movies.model.domain.Genre
import com.marlena.cubosapp_movies.model.domain.Movie
import com.marlena.cubosapp_movies.service.MovieClient

class MovieRepository {

    private val movies = mutableListOf<Movie>()
    private val genres = mutableListOf<Genre>()

    fun getMovieList(): List<Movie>? {
        if (movies.isNullOrEmpty()) {
            val response = MovieClient.instance.callGetMovies()
            response?.results?.let {
                movies.clear()
                movies.addAll(it)
            }
        }
        return movies
    }

    fun getGenreList(): List<Genre>? {
        if (genres.isNullOrEmpty()) {
            val response = MovieClient.instance.callGetGenres()
            response?.genres?.let {
                genres.clear()
                genres.addAll(it)
            }
        }
        return genres
    }

    fun getResultSearchList(querySearch: String): List<Movie>? {
        val response = MovieClient.instance.callGetResultSearch(querySearch)
        return response?.results
    }
}