package com.marlena.cubosapp_movies.scenes.search

import android.content.Context
import com.marlena.cubosapp_movies.model.domain.Movie

interface Search {
    interface View {
        fun setSearchList(result:  List<Movie>)
        fun displayFailure(error: Int)
        fun getViewContext(): Context?
    }
    interface Presenter {
        fun getSearchList(querySearch: String)
        fun kill()
    }
}