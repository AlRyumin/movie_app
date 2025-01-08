package com.example.movieapp.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.base.utils.GroupMoviesByMonth
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.GetFavoritesUseCase
import com.example.movieapp.domain.usecase.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavorites: GetFavoritesUseCase,
    private val removeFromFavorite: RemoveFromFavoritesUseCase,
) : ViewModel() {
    private val _movies = MutableStateFlow<Map<String, List<Movie>>>(emptyMap())
    val movies = _movies.asStateFlow()

    fun removeFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFromFavorite(movie)
            loadMovies()
        }
    }

    fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _movies.value = GroupMoviesByMonth(getFavorites())
        }
    }
}