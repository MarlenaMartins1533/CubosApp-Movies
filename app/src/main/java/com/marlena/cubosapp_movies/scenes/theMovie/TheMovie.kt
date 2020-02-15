package com.marlena.cubosapp_movies.scenes.theMovie

interface TheMovie {
    interface View {
        fun showMessage(message: String)
        fun onBackPressed()
    }

    interface Presenter {
    }
}