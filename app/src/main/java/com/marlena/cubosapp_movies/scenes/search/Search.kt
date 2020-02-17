package com.marlena.cubosapp_movies.scenes.search

import android.content.Context

interface Search {
    interface View {
        fun getViewContext(): Context?
    }
    interface Presenter {}
}