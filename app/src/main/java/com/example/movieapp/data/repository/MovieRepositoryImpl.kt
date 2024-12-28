package com.example.movieapp.data.repository

import com.example.movieapp.data.source.local.LocalDataSource
import com.example.movieapp.data.source.remote.RemoteDataSource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : MovieRepository {
    override val movies: Flow<List<Movie>>
        get() = localDataSource.getAll()

    override val favoriteMovies: Flow<List<Movie>>
        get() = localDataSource.getAll()

    override suspend fun fetch(page: Int?): Result<List<Movie>> {
        try {

            return remoteDataSource.getMovies(page).fold(
                onSuccess = { movies ->
                    if (page == null) {
                        localDataSource.insertAll(movies)
                    }

                    Result.success(movies)
                },
                onFailure = {
                    Result.failure(exception = it)
                }
            )
        } catch (e: Exception) {
            return Result.failure(exception = e)
        }
    }
}