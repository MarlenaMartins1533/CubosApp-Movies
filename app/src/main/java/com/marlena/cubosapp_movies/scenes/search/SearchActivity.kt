package com.marlena.cubosapp_movies.scenes.search

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marlena.cubosapp_movies.R
import com.marlena.cubosapp_movies.model.domain.Movie
import com.marlena.cubosapp_movies.scenes.adapter.MovieAdapter
import com.marlena.cubosapp_movies.scenes.theMovie.TheMovieActivity
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), Search.View, MovieAdapter.Listener {
    private val searchList = mutableListOf<Movie>()
    private lateinit var presenter: SearchPresenter
    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbarSearch)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = SearchPresenter(this)

        setupAdapters()
        setupViews()
    }

    override fun setSearchList(result: List<Movie>) {
        searchList.clear()
        searchList.addAll(result)
        adapter?.notifyDataSetChanged()
    }

    private fun setupAdapters() {
        adapter = MovieAdapter(searchList, this)
        adapter?.notifyDataSetChanged()
    }

    private fun setupViews() {
        recyclerViewRV?.adapter = adapter
        searchBTN.setOnClickListener {
            onSearchSubmit()
        }
    }

    private fun onSearchSubmit() {
        val querySearch = searchEDT.text.toString()
        if (querySearch.isNotEmpty()) {
            presenter.getSearchList(querySearch)
        }
    }

    override fun displayFailure(error: Int) {
        when (error) {
            4 -> Toast.makeText(this, getString(R.string.erro4), Toast.LENGTH_LONG).show()
            5 -> Toast.makeText(this, getString(R.string.erro5), Toast.LENGTH_LONG).show()
            else -> Toast.makeText(this, getString(R.string.erro0), Toast.LENGTH_LONG).show()
        }
    }

    override fun getViewContext(): Context? {
        return this
    }

    override fun openMovieFragment(movie: Movie, itemView: View) {
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            Pair(itemView, TheMovieActivity.TRANSITION_IMAGE)
        )

        val intent = Intent(this, TheMovieActivity::class.java).apply {
            putExtra("imageTitle", movie.title)
            putExtra("imagePosterPath", (movie.poster_path))
            putExtra("imageOverview", (movie.overview))
        }
        startActivity(intent, options.toBundle())
        adapter?.notifyDataSetChanged()
    }
}