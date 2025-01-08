package com.example.movieapp.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.ui.component.MovieList
import com.example.movieapp.ui.utils.SetTopBarColor

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SetTopBarColor(MaterialTheme.colorScheme.primary)

    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is HomeUiState.Loading -> {
                LoadingScreen()
            }

            is HomeUiState.Error -> {
                ErrorScreen(
                    message = (uiState as HomeUiState.Error).message,
                )
            }

            is HomeUiState.Content -> {
                MovieList(
                    contentState = (uiState as HomeUiState.Content<Map<String, List<Movie>>>).contentState,
                ) { movie ->
                    viewModel.toggleFavorite(movie)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(40.dp)
                .size(100.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 5.dp,
        )
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = message,
            modifier = Modifier
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
