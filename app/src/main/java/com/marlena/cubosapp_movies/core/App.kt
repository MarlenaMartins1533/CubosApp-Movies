package com.marlena.cubosapp_movies.core

import android.app.Application
import com.marlena.cubosapp_movies.repository.MovieRepository
import com.marlena.cubosapp_movies.service.MovieClient

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MovieClient.initialize()
    }

    companion object {
        val movieRepository = MovieRepository()
    }
}