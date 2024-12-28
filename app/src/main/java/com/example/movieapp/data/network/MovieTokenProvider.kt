package com.example.movieapp.data.network

import com.example.movieapp.BuildConfig
import javax.inject.Inject

class MovieTokenProvider @Inject constructor() {
    fun getToken(): String {
        return BuildConfig.THEMOVIEDB_ACCESS_TOKEN
    }
}