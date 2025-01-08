package com.example.movieapp.ui.screen.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.ui.component.MovieHeader
import com.example.movieapp.ui.component.MovieItem

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val movies = viewModel.movies.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        movies.forEach { (monthYear, movieList) ->
            item {
                MovieHeader(title = monthYear)
            }
            items(movieList) { movie ->
                MovieItem(
                    movie = movie,
                    toggleIsFavorite = { viewModel.removeFavorite(movie) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                VerticalDivider(modifier = Modifier.height(8.dp))
            }
        }
    }
}