package com.marlena.cubosapp_movies.scenes.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.marlena.cubosapp_movies.R
import com.marlena.cubosapp_movies.scenes.search.SearchFragment
import com.marlena.cubosapp_movies.scenes.search.SearchFragment.Companion.SEARCHTEXT_ARG
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), Main.View {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        initListener()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_movies,
                R.id.navigation_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun initListener() {
        searchBTN.setOnClickListener {
            if (searchEDT.isVisible) {
                getEDTsOpenSearch()
            } else searchEDT.visibility = View.VISIBLE
        }
    }

    private fun getEDTsOpenSearch() {
        SearchFragment.newInstance(searchEDT.text.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}