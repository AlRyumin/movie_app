package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    val repository: MovieRepository,
) {
    suspend operator fun invoke(movie: Movie) {
        repository.addToFavorites(movie)
    }
}