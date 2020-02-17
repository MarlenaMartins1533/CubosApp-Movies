package com.marlena.cubosapp_movies.scenes.moviePage

import com.marlena.cubosapp_movies.core.App
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviePresenter(private val view: Movie.View) : Movie.Presenter, CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var job: Job? = null

    override fun makeRequests() {
        job = launch {
            withContext(Dispatchers.IO) {
                App.movieRepository.getMovieList()
                App.movieRepository.getGenreList()
            }
            view.setupPageAdapter()
        }
    }

    override fun kill() {
        job?.cancel()
    }
}