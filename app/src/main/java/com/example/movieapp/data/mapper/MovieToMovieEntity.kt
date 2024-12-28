package com.example.movieapp.data.mapper

import com.example.movieapp.base.utils.Mapper
import com.example.movieapp.data.source.local.MovieEntity
import com.example.movieapp.domain.model.Movie

object MovieToMovieEntity : Mapper<Movie, MovieEntity> {
    override fun mapFrom(from: Movie): MovieEntity {
        return MovieEntity(
            id = from.id,
            adult = from.adult,
            backdropPath = from.backdropPath,
            genreIds = from.genreIds,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            releaseDate = from.releaseDate,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount,
        )
    }
}