package com.example.movieapp.base.utils

import com.example.movieapp.domain.model.Movie

object GroupMoviesByMonth {
    operator fun invoke(movies: List<Movie>): Map<String, List<Movie>> {
        return movies.groupBy { movie ->
            ReleaseDateFormat(movie.releaseDate)
        }
    }
}