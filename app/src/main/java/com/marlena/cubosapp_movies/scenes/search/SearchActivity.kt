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
import com.marlena.cubosapp_movies.core.App
import com.marlena.cubosapp_movies.model.domain.Movie
import com.marlena.cubosapp_movies.scenes.adapter.MovieAdapter
import com.marlena.cubosapp_movies.scenes.theMovie.TheMovieActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.searchBTN
import kotlinx.android.synthetic.main.activity_search.searchEDT
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_movie_list.recyclerViewRV

class SearchActivity : AppCompatActivity(), Search.View, MovieAdapter.Listener {
    private var searchText: String? = " "
    private lateinit var movieList: List<Movie>
    private val resultList = mutableListOf<Movie>()
    private var adapter: MovieAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbarSearch)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        searchText = intent.getStringExtra(SEARCH_ARG) ?: " "
//         = searchText
        initListener()
        getMoviesList()
        onSearchSubmit()
    }

    private fun onSearchSubmit() {
        val text = searchEDT.text.toString()
        searchText = text
        resultList.clear()

        if (searchText.isNullOrEmpty()) setupAdapters(movieList)
        else {
            movieList.forEach {
                if (it.title.contains(text, ignoreCase = false)) resultList.add(it)
            }
            if (resultList.isNullOrEmpty()) setupAdapters(movieList)
            else setupAdapters(resultList)
        }
        setupViews()
    }

    private fun initListener() {
        searchBTN.setOnClickListener {
            onSearchSubmit()
        }
    }
    private fun getMoviesList() {
        val list = App.movieRepository.getMovieList()

        if (list.isNullOrEmpty())
            showMessage("Filmes não foram carregados,verifique sua conexão.")
        else movieList = list
    }

    private fun setupAdapters(list: List<Movie>) {
        adapter =
            MovieAdapter(
                list,
                this
            )
    }

    private fun setupViews() {
        recyclerViewRV?.adapter = adapter
    }

    private fun showMessage(message: String) {
        Toast.makeText(getViewContext(), message, Toast.LENGTH_LONG).show()
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
//
//    companion object {
//        const val SEARCH_ARG = "search_arg"
//    }

}