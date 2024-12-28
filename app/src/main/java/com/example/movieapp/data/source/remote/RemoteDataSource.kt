package com.example.movieapp.data.source.remote

import com.example.movieapp.domain.model.Movie

interface RemoteDataSource {
    suspend fun getMovies(page: Int?): Result<List<Movie>>
}