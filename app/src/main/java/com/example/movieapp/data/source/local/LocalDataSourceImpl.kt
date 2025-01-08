package com.example.movieapp.data.source.local

import com.example.movieapp.data.mapper.MovieEntityToMovie
import com.example.movieapp.data.mapper.MovieToMovieEntity
import com.example.movieapp.data.source.local.dao.MovieDao
import com.example.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : LocalDataSource {
    override fun getAll(): Flow<List<Movie>> = dao.getAll().map {
        MovieEntityToMovie.mapAll(it)
    }

    override suspend fun getMovie(id: Int): Movie? {
        val movieEntity = dao.getMovie(id)
        return if (movieEntity != null)
            MovieEntityToMovie.mapFrom(movieEntity)
        else null
    }


    override suspend fun insertAll(movies: List<Movie>) {
        dao.insertAll(MovieToMovieEntity.mapAll(movies))
    }

    override suspend fun getFavorites(): List<Movie> =
        MovieEntityToMovie.mapAll(dao.getFavorites())


    override suspend fun addToFavorites(movie: Movie) {
        dao.insert(MovieToMovieEntity.mapFrom(movie.copy(isFavorite = true)))
    }

    override suspend fun removeFromFavorites(id: Int) {
        dao.delete(id)
    }
}