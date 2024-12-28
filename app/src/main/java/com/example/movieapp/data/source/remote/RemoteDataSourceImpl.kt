package com.example.movieapp.data.source.remote

import com.example.movieapp.domain.model.Movie
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieApiService,
) : RemoteDataSource {
    override suspend fun getMovies(page: Int?): Result<List<Movie>> =
        try {
            apiService.getMovies(page).let { movieResult ->
                Result.success(movieResult.results)
            }
        } catch (e: IOException) {
            Result.failure(e)
        }
}