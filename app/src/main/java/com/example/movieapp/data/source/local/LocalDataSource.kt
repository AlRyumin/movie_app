package com.example.movieapp.data.source.local

import com.example.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAll(): Flow<List<Movie>>

    suspend fun getMovie(id: Int): Movie?

    suspend fun insertAll(movies: List<Movie>)

    suspend fun getFavorites(): List<Movie>

    suspend fun addToFavorites(movie: Movie)

    suspend fun removeFromFavorites(id: Int)
}