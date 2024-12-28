package com.example.movieapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.source.local.converter.GenreIdsConverter
import com.example.movieapp.data.source.local.dao.MovieDao

@TypeConverters(GenreIdsConverter::class)
@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}