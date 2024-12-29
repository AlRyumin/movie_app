package com.example.movieapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.base.utils.GroupMoviesByMonth
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState<Map<String, List<Movie>>>>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState<Map<String, List<Movie>>>> = _uiState.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())

    init {
        _uiState.value = HomeUiState.Loading
        fetchMovies()
        viewModelScope.launch {
            delay(2000)
            fetchMovies(2)
            delay(2000)
            fetchMovies(3)
        }
    }

    fun fetchMovies(page: Int? = null){
        viewModelScope.launch(Dispatchers.IO) {
            val result = fetchMoviesUseCase(page)

            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _movies.value += it
                    _uiState.value = HomeUiState.Content(GroupMoviesByMonth(_movies.value))
                }
            } else {
                result.exceptionOrNull().let {
                    _uiState.value = HomeUiState.Error(it?.message ?: "Something went wrong")
                }
            }
        }
    }
}