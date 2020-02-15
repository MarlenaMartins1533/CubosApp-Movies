package com.marlena.cubosapp_movies.scenes.moviePage

import android.util.Log
import com.marlena.cubosapp_movies.service.MovieClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class MoviePresenter(private val view: Movie.View) : Movie.Presenter {
//    override var job: Job? = null
//
//    override fun getGenres() {
//        job = launch {
//            val response = withContext(Dispatchers.IO) { MovieClient.instance.getGenres() }
//            view.setGenresList(response)
//            Log.d("LENA", response?.genres?.get(0)?.name)
//        }
//    }
//
//    override fun kill() {
//        job?.cancel()
//    }
}