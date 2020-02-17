package com.marlena.cubosapp_movies.scenes.theMovie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setTransitionName
import com.marlena.cubosapp_movies.R
import com.marlena.cubosapp_movies.data.Constants.imageUrlMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_themovie.*

class TheMovieActivity : AppCompatActivity(), TheMovie.View {
    private lateinit var presenter: TheMoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_themovie)

        presenter = TheMoviePresenter(this)

        val posterPath = intent.getStringExtra("imagePosterPath") ?: ""
        val movieTitle = intent.getStringExtra("imageTitle") ?: ""
        val movieOverview = intent.getStringExtra("imageOverview") ?: ""

        setTransitionName(movieIMG, TRANSITION_IMAGE)

        if (posterPath.isEmpty())
            movieIMG.setImageResource(R.drawable.alerta_790x400)
        else setImageMovie(imageUrlMovie + posterPath)

        titleTXT.text = movieTitle
        if (movieOverview.isNotEmpty()) overviewTXT.text = movieOverview
        else {
            val message = "Não há descrição!"
            overviewTXT.text = message
            showMessage(message)
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
