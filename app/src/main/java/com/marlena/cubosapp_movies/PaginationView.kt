package com.marlena.cubosapp_movies

import androidx.recyclerview.widget.RecyclerView

abstract class PaginationView {
    var page: Int = 0
    var noMorePage: Boolean = false
//    var onScrollListener = object : RecyclerView.onScrollListener() {
//        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            super.onScrollStateChanged(recyclerView, newState)
//
//            if (!noMorePage && !recyclerView.canScrollVertically(1)) {
//                requestNextPage()
//            }
//        }
//    }
    abstract fun showProgressBar()
    abstract fun hideProgressBar()
    abstract fun makeRequests()

    fun requestNextPage() {
        showProgressBar()
        noMorePage = false
        makeRequests()
        page++
    }

    fun theEndPagination() {
        page = 0
        noMorePage = true
        hideProgressBar()
    }
}