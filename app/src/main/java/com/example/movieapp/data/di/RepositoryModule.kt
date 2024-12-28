package com.example.movieapp.data.di

import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.data.source.local.LocalDataSource
import com.example.movieapp.data.source.local.LocalDataSourceImpl
import com.example.movieapp.data.source.remote.RemoteDataSource
import com.example.movieapp.data.source.remote.RemoteDataSourceImpl
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    abstract fun bindMoviesRepository(repository: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieLocalSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindMovieRemoteSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}