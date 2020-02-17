package com.marlena.cubosapp_movies.scenes.moviesList

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
import com.marlena.cubosapp_movies.model.domain.Genre
import com.marlena.cubosapp_movies.model.domain.Movie
import com.marlena.cubosapp_movies.scenes.adapter.MovieAdapter
import com.marlena.cubosapp_movies.scenes.theMovie.TheMovieActivity
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MoviesListFragment : Fragment(), MoviesList.View, MovieAdapter.Listener {

    private val movieList = mutableListOf<Movie>()
    private val movieListByGenre = mutableListOf<Movie>()
    private lateinit var presenter: MoviesListPresenter
    private var genrePage: String = " "
    private var adapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = MoviesListPresenter(this)
        genrePage = arguments?.getString(GENRE_PAGE) ?: " "
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeRequests()
        setupAdapters()
        setupViews()
    }

    private fun makeRequests() {
        presenter.getMovieList()
    }

    private fun setupAdapters() {
        adapter =
            MovieAdapter(
                movieListByGenre,
                this
            )
    }

    private fun setupViews() {
        recyclerViewRV?.adapter = adapter
    }

    override fun setList(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)
        presenter.getGenreList()
    }

    override fun setGenreList(genreList: List<Genre>?) {
        presenter.getMovieListByGenre(genrePage, movieList, genreList)
    }

    override fun setMovieListByGenre(list: List<Movie>) {
        movieListByGenre.clear()
        movieListByGenre.addAll(list)
        adapter?.notifyDataSetChanged()

    }

    override fun displayFailure(error: Int) {
        when (error) {
            1 -> Toast.makeText(context, getString(R.string.erro1), Toast.LENGTH_LONG).show()
            2 -> Toast.makeText(context, getString(R.string.erro2), Toast.LENGTH_LONG).show()
            3 -> Toast.makeText(context, getString(R.string.erro3), Toast.LENGTH_LONG).show()
            else -> Toast.makeText(context, getString(R.string.erro0), Toast.LENGTH_LONG).show()
        }
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
            putExtra("imageOverview", (movie.overview))
            putExtra("imagePosterPath", (movie.poster_path))
        }
        activity?.startActivity(intent, options.toBundle())
        adapter?.notifyDataSetChanged()
    }

    companion object {
        const val GENRE_PAGE = "GENRE_PAGE"
        fun newInstance(genrePage: String) = MoviesListFragment().apply {
            arguments = Bundle().apply {
                putString(GENRE_PAGE, genrePage)
            }
        }
    }
}