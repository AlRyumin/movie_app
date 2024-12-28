package com.example.movieapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _uiState = MutableStateFlow<HomeUiState<List<Movie>>>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState<List<Movie>>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = HomeUiState.Loading

            val result = fetchMoviesUseCase(null)

            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _uiState.value = HomeUiState.Content(it)
                }
            } else {
                result.exceptionOrNull().let {
                    _uiState.value = HomeUiState.Error(it?.message ?: "Something went wrong")
                }
            }
        }
    }
}