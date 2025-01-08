package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie

interface MovieRepository {
    suspend fun getFavorites(): List<Movie>

    suspend fun getMovie(id: Int): Movie?

    suspend fun fetch(page: Int? = 0): Result<List<Movie>>

    suspend fun addToFavorites(movie: Movie)

    suspend fun removeFromFavorites(id: Int)
}