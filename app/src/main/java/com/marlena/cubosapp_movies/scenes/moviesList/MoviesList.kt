package com.marlena.cubosapp_movies.scenes.moviesList

import android.content.Context
import com.marlena.cubosapp_movies.model.domain.Genre
import com.marlena.cubosapp_movies.model.domain.Movie
import com.marlena.cubosapp_movies.model.response.GenreResponse

interface MoviesList {
    interface View {
        fun displayFailure(error: Int)
        fun makeRequests()
        fun setList(list: List<Movie>)
        fun setMovieListByGenre(list: List<Movie>)
        fun setGenreList(genreList: List<Genre>?)
        fun getViewContext(): Context?
    }

    interface Presenter {
        fun getMovieListByGenre(
            genrePage: String,
            movieList: List<Movie>,
            genreList: List<Genre>?
        )

        fun getMovieList()
        fun getGenreList()
        fun kill()
    }
}