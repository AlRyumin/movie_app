package com.example.movieapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import com.example.movieapp.domain.model.Movie

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is HomeUiState.Loading -> Text("loading")
            is HomeUiState.Refreshing -> Text("refreshing")
            is HomeUiState.LoadingMore -> Text("loading More")
            is HomeUiState.Error -> Text("error")
            is HomeUiState.Content ->
             MovieList(
                 (uiState as HomeUiState.Content<List<Movie>>).data
             )
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies) { movie ->
            Text(movie.title)
        }
    }
}