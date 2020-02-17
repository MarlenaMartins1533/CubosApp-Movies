package com.marlena.cubosapp_movies.scenes.moviePage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.marlena.cubosapp_movies.R
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_page_genre.*

class MovieFragment : Fragment(), Movie.View {

    private lateinit var presenter: MoviePresenter
    private var sectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MoviePresenter(this)
        presenter.makeRequests()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_genre, container, false)
    }

    override fun setupPageAdapter() {
        sectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager, this)
        container.adapter = sectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    override fun getViewContext(): Context? {
        return context
    }

}