package com.example.movieapp.base.utils

import java.text.SimpleDateFormat
import java.util.Locale

object ReleaseDateFormat {
    operator fun invoke(date: String): String{
        val formatedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)
        val dateFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
        return formatedDate?.let { dateFormat.format(it) } ?: ""
    }
}