package com.marlena.cubosapp_movies.service

import android.util.Log
import com.marlena.cubosapp_movies.data.Constants
import com.marlena.cubosapp_movies.model.response.GenreResponse
import com.marlena.cubosapp_movies.model.response.MovieResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MovieClient {

    private lateinit var movieApi: MovieApi

    fun callGetMovies(): MovieResponse? {
        val call = movieApi.getMovies()
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                Log.d("LENA", "getMovies successful: ${response.body()?.toString()}")
                return response.body()
            } else {
                Log.d("LENA", "getMovies Response Error: ${response.errorBody()?.toString()}")
            }
        } catch (e: IOException) {
            Log.e("IOException", e.message)
        } catch (e: RuntimeException) {
            Log.e("Runtime Exception", e.message)
        }
        return null
    }

    fun callGetGenres(): GenreResponse? {
        val call = movieApi.getGenres()

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                Log.d("LENA", "getGenres successful: ${response.body()?.toString()}")
                return response.body()
            } else {
                Log.d("LENA", "getGenres Response Error: ${response.errorBody()?.toString()}")
            }
        } catch (e: IOException) {
            Log.e("LENA", e.message)
        } catch (e: RuntimeException) {
            Log.e("LENA", e.message)
        }
        return null
    }

    companion object {
        lateinit var instance: MovieClient
            private set

        fun initialize() {
            instance =
                MovieClient()
            instance.movieApi = Retrofit.Builder()
                .baseUrl(Constants.baseUrlMovie)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }
    }
}