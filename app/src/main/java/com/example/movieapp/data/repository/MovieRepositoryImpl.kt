package com.example.movieapp.data.repository

import com.example.movieapp.data.source.local.LocalDataSource
import com.example.movieapp.data.source.remote.RemoteDataSource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : MovieRepository {
    override suspend fun getFavorites(): List<Movie> = localDataSource.getFavorites()

    override suspend fun getMovie(id: Int): Movie? =
        localDataSource.getMovie(id)

    fun mergeMovies(remote: List<Movie>, local: List<Movie>): List<Movie> {
        // Create a map from the local list for quick lookup
        val localMap = local.associateBy { it.id }

        // Merge the two lists
        val mergedList = remote.map { remoteMovie ->
            // Check if the movie exists in the local map
            val localMovie = localMap[remoteMovie.id]
            // If it exists, prioritize the isFavorite value from the local movie
            if (localMovie != null) {
                remoteMovie.copy(isFavorite = localMovie.isFavorite)
            } else {
                remoteMovie
            }
        }.toMutableList()

        // Add any movies that are in local but not in remote
        val remoteIds = remote.map { it.id }.toSet()
        mergedList += local.filter { it.id !in remoteIds }

        return mergedList
    }

    override suspend fun fetch(page: Int?): Result<List<Movie>> {
        try {

            return remoteDataSource.getMovies(page).fold(
                onSuccess = { remoteMovies ->
                    val movies = mergeMovies(remoteMovies, getFavorites())
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

    override suspend fun addToFavorites(movie: Movie) {
        localDataSource.addToFavorites(movie)
    }

    override suspend fun removeFromFavorites(id: Int) {
        localDataSource.removeFromFavorites(id)
    }
}