package com.example.movieapp.data.source.local.converter

import androidx.room.TypeConverter

class GenreIdsConverter {
    @TypeConverter
    fun genreIdsToList(genreIdsStr: String): List<Int> {
        val s = genreIdsStr.replace("(", "").replace(")", "")
        val list = s.split(",")
        val genreIds: List<Int> = list.map { it.toInt() }
        return genreIds
    }

    @TypeConverter
    fun listToGenreIds(genreIds: List<Int>) = genreIds.toString()
}