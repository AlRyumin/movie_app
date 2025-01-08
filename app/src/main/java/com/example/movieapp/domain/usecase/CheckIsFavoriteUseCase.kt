package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.repository.MovieRepository
import timber.log.Timber
import javax.inject.Inject

class CheckIsFavoriteUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(id: Int): Boolean {
        val movie = repository.getMovie(id)

        Timber.d("moviesds -> ${movie?.id} ${movie?.isFavorite}")

        return movie != null && movie.isFavorite
    }
}