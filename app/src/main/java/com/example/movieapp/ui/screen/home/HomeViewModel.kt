package com.example.movieapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.base.utils.GroupMoviesByMonth
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<HomeUiState<Map<String, List<Movie>>>>(HomeUiState.Loading)

    val uiState: StateFlow<HomeUiState<Map<String, List<Movie>>>> = _uiState.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())

    private val _page = MutableStateFlow(1)

    init {
        loadMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = fetchMoviesUseCase(_page.value)

            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _movies.value += it

                    _uiState.value = HomeUiState.Content(
                        ContentState(
                            data = GroupMoviesByMonth(_movies.value),
                            refresh = { refreshMovies() },
                            loadMore = { loadMore() },
                        )
                    )

                    _page.value = _page.value.plus(1)
                }
            } else {
                result.exceptionOrNull().let {
                    _uiState.value = HomeUiState.Error(it?.message ?: "Something went wrong")
                }
            }
        }
    }

    fun loadMovies() {
        _uiState.value = HomeUiState.Loading
        fetchMovies()
    }

    private fun refreshMovies() {
        val currentState = (_uiState.value as? HomeUiState.Content)?.contentState
        if (currentState != null) {
            _uiState.value = HomeUiState.Content(
                currentState.copy(isRefreshing = true, isLoadingMore = false)
            )
        }
        fetchMovies()
    }

    private fun loadMore() {
        val currentState = (_uiState.value as? HomeUiState.Content)?.contentState
        if (currentState != null) {
            _uiState.value = HomeUiState.Content(
                currentState.copy(isRefreshing = false, isLoadingMore = true)
            )
            fetchMovies()
        }
    }
}