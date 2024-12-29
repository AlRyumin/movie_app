package com.example.movieapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
            is HomeUiState.Loading -> Text("loading")
            is HomeUiState.Refreshing -> Text("refreshing")
            is HomeUiState.LoadingMore -> Text("loading More")
            is HomeUiState.Error -> Text("error")
            is HomeUiState.Content ->
             MovieList(
                 (uiState as HomeUiState.Content<Map<String, List<Movie>>>).data
             )
        }
    }
}

