package com.marlena.cubosapp_movies.scenes.moviePage

import android.content.Context

interface Movie {
    interface View {
        fun getViewContext(): Context?
    }

    interface Presenter {
    }
}