package com.marlena.cubosapp_movies.repository

import com.marlena.cubosapp_movies.model.domain.Movie
import com.marlena.cubosapp_movies.model.response.GenreResponse
import com.marlena.cubosapp_movies.service.MovieClient

class MovieRepository {

    fun getMovieList(): List<Movie>? {
        val response = MovieClient.instance.callGetMovies()
        return response?.results
    }

    fun getGenreList(): GenreResponse? {
        return MovieClient.instance.callGetGenres()
    }
}