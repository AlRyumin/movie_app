package com.example.movieapp.ui.screen.home

sealed class HomeUiState<out T> {
    data object Loading : HomeUiState<Nothing>()
    data object Refreshing : HomeUiState<Nothing>()
    data object LoadingMore : HomeUiState<Nothing>()
    data class Error(val message: String) : HomeUiState<Nothing>()
    data class Content<T>(val data: T) : HomeUiState<T>()
}