package com.example.movieapp.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.domain.model.Movie

@Composable
fun MovieList(movies: Map<String, List<Movie>>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        movies.forEach { (monthYear, movieList) ->
            item {
                MovieHeader(title = monthYear)
            }
            items(movieList) { movie ->
                MovieItem(movie = movie, modifier = Modifier.padding(horizontal = 16.dp))
                VerticalDivider(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun MovieHeader(title: String) {
    Text(
        title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        style = MaterialTheme.typography.titleSmall
    )
}

