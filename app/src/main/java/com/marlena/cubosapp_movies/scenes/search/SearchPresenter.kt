package com.marlena.cubosapp_movies.scenes.search

import com.marlena.cubosapp_movies.core.App
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchPresenter(val view: Search.View) : Search.Presenter, CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var job: Job? = null

    override fun getSearchList(querySearch: String) {
        job = launch {
            val result = withContext(Dispatchers.IO) {
                App.movieRepository.getResultSearchList(querySearch)
            }
            if (result.isNullOrEmpty())
                view.displayFailure(4)
            else
                view.setSearchList(result)
        }
    }

    override fun kill() {
        job?.cancel()
    }
}