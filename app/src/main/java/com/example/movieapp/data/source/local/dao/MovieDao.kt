package com.example.movieapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.source.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from movies")
    fun getAll(): Flow<List<MovieEntity>>

    @Query("SELECT * from movies WHERE isFavorite=1 ORDER by releaseDate DESC")
    suspend fun getFavorites(): List<MovieEntity>

    @Query("SELECT * from movies WHERE id=:id")
    suspend fun getMovie(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE id=:id")
    fun delete(id: Int)
}