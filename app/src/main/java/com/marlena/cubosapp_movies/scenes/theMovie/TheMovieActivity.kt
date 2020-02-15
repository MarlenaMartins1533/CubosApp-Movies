package com.marlena.cubosapp_movies.scenes.theMovie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setTransitionName
import com.marlena.cubosapp_movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_themovie.*

class TheMovieActivity : AppCompatActivity(), TheMovie.View {

    private lateinit var presenter: TheMoviePresenter
    lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_themovie)

        presenter = TheMoviePresenter(this)

        val poster_path = intent.getStringExtra("imagePosterPath") ?: ""
        val backdrop_path = intent.getStringExtra("imageBackdropPath") ?: ""
        val title = intent.getStringExtra("imageTitle") ?: ""
        val overview = intent.getStringExtra("imageOverview") ?: ""

        setTransitionName(movieIMG, TRANSITION_IMAGE)

        if (poster_path.isEmpty()) {
            movieIMG.setImageResource(R.drawable.alerta_790x400)
        } else {
            if (backdrop_path.isEmpty()) setImageMovie(poster_path)
            setImageMovie(backdrop_path)
        }
    }

    private fun setImageMovie(url_image: String) {
        Picasso.get().load(url_image).into(movieIMG)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        const val TRANSITION_IMAGE = "image"
    }
}
