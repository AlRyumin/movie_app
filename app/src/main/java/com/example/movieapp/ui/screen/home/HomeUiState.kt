package com.example.movieapp.ui.screen.home

data class ContentState<T>(
    val data: T,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false,
    val refresh: () -> Unit,
    val loadMore: () -> Unit
)

sealed class HomeUiState<out T> {
    object Loading : HomeUiState<Nothing>()
    data class Error(val message: String) : HomeUiState<Nothing>()
    data class Content<T>(val contentState: ContentState<T>) : HomeUiState<T>()
}