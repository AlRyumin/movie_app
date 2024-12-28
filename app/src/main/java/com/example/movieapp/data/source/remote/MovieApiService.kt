package com.example.movieapp.data.source.remote

import com.example.movieapp.domain.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService {
    @GET("discover/movie?vote_average.gte=7&vote_count.gte=100&sort_by=primary_release_date.desc")
    suspend fun getMovies(
        @Query("page") page: Int? = 1,
    ): MovieResponse
}