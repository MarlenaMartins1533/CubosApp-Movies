package com.marlena.cubosapp_movies.scenes.search

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.marlena.cubosapp_movies.R
import com.marlena.cubosapp_movies.core.App
import com.marlena.cubosapp_movies.model.domain.Movie
import com.marlena.cubosapp_movies.scenes.adapter.MovieAdapter
import com.marlena.cubosapp_movies.scenes.theMovie.TheMovieActivity
import kotlinx.android.synthetic.main.fragment_movie_list.*

class SearchFragment : Fragment(), Search.View, MovieAdapter.Listener {
    private var searchText: String? = " "
    private lateinit var movieList: List<Movie>
    private val resultList = mutableListOf<Movie>()
    private var adapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchText = arguments?.getString(SEARCHTEXT_ARG) ?: " "
        getMoviesList()
        onSearchSubmit()
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    private fun onSearchSubmit() {
        val text = searchText
        resultList.clear()

        if (text!!.isNotEmpty()) setupAdapters(movieList)
        else {
            movieList.forEach {
                if (it.title.contains(text, ignoreCase = false)) resultList.add(it)
            }
            setupAdapters(resultList)
        }
        setupViews()
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
        return context
    }

    override fun openMovieFragment(movie: Movie, itemView: View) {
        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity,
            Pair(itemView, TheMovieActivity.TRANSITION_IMAGE)
        )

        val intent = Intent(context, TheMovieActivity::class.java).apply {
            putExtra("imageTitle", movie.title)
            putExtra("imagePosterPath", (movie.poster_path))
            putExtra("imageOverview", (movie.overview))
        }
        activity?.startActivity(intent, options.toBundle())
        adapter?.notifyDataSetChanged()
    }

    companion object {
        const val SEARCHTEXT_ARG = "searchText_arg"
        fun newInstance(searchText: String) = SearchFragment().apply {
            arguments = Bundle().apply {
                putString(SEARCHTEXT_ARG, searchText)
            }
            return SearchFragment()
        }
    }
}