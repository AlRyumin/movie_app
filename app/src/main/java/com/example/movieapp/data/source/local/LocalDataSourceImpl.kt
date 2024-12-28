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

    override suspend fun insertAll(movies: List<Movie>) {
        dao.insertAll(MovieToMovieEntity.mapAll(movies))
    }

    override fun getFavorites(): Flow<List<Movie>> = getAll()

    override suspend fun addToFavorites(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorites(movie: Movie) {
        TODO("Not yet implemented")
    }
}