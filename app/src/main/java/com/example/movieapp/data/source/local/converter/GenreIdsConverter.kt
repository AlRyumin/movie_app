package com.example.movieapp.data.source.local.converter

import androidx.room.TypeConverter

class GenreIdsConverter {
    @TypeConverter
    fun genreIdsToList(genreIdsStr: String): List<Int> {
        return genreIdsStr.split(",").map { it.trim().toInt() }
    }

    @TypeConverter
    fun listToGenreIds(genreIds: List<Int>): String {
        return genreIds.joinToString(separator = ",")
    }
}