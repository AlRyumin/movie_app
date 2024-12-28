package com.example.movieapp.data.mapper

import com.example.movieapp.base.utils.Mapper
import com.example.movieapp.data.source.local.MovieEntity
import com.example.movieapp.domain.model.Movie

object MovieEntityToMovie : Mapper<MovieEntity, Movie> {
    override fun mapFrom(from: MovieEntity): Movie {
        return Movie(
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