package com.marlena.cubosapp_movies.scenes.moviesList

import android.widget.Toast
import com.marlena.cubosapp_movies.core.App
import com.marlena.cubosapp_movies.model.domain.Movie
import com.marlena.cubosapp_movies.model.response.GenreResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesListPresenter(private val view: MoviesList.View) : MoviesList.Presenter,
    CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var job: Job? = null

    override fun getMovieListByGenre(
        genrePage: String,
        movieList: List<Movie>,
        genreResponse: GenreResponse?
    ) {
        val list = mutableListOf<Movie>()

        genreResponse?.genres?.forEach { g ->
            if (g.name == genrePage) {
                Toast.makeText(view.getViewContext(), genrePage, Toast.LENGTH_LONG).show()
                movieList.forEach { m ->
                    m.genreIds?.forEach { it ->
                        if (it == g.id) list.add(m)
                    }
                }
            }
        }
        if (list.isNullOrEmpty()) view.displayFailure(2)
        else view.setMovieListByGenre(list)
    }

    override fun getMovieList() {
        job = launch {
            val result = withContext(Dispatchers.IO) {
                App.movieRepository.getMovieList()
            }

            if (result.isNullOrEmpty())
                view.displayFailure(1)
            else view.setList(result)
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