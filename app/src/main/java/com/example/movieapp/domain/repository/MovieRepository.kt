package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    val movies: Flow<List<Movie>>

    val favoriteMovies: Flow<List<Movie>>

    suspend fun fetch(page: Int? = 0): Result<List<Movie>>

    suspend fun addToFavorites(movie: Movie)

    suspend fun removeFromFavorites(id: Int)
}