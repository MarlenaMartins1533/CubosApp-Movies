package com.marlena.cubosapp_movies.scenes.moviesList

import com.marlena.cubosapp_movies.core.App
import com.marlena.cubosapp_movies.model.domain.Genre
import com.marlena.cubosapp_movies.model.domain.Movie
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesListPresenter(private val view: MoviesList.View) : MoviesList.Presenter,
    CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var job: Job? = null

    override fun getMovieListByGenre(
        genrePage: String,
        movieList: List<Movie>,
        genreList: List<Genre>?
    ) {
        val list = mutableListOf<Movie>()

        genreList?.forEach { genre ->
            if (genre.name == genrePage) {
                movieList.forEach { movie ->
                    movie.genreIds?.forEach { genreId ->
                        if (genreId == genre.id) list.add(movie)
                    }
                }
            }
        }
        if (list.isNullOrEmpty())
            view.displayFailure(2)
        else
            view.setMovieListByGenre(list)
    }

    override fun getMovieList() {
        job = launch {
            val result = withContext(Dispatchers.IO) {
                App.movieRepository.getMovieList()
            }
            if (result.isNullOrEmpty())
                view.displayFailure(1)
            else
                view.setList(result)
        }
    }

    override fun getGenreList() {
        job = launch {
            val result = withContext(Dispatchers.IO) {
                App.movieRepository.getGenreList()
            }
            view.setGenreList(result)
        }
    }

    override fun kill() {
        job?.cancel()
    }
}