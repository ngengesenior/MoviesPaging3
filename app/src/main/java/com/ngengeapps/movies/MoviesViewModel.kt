package com.ngengeapps.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ngengeapps.movies.models.Movie
import kotlinx.coroutines.flow.Flow

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    private var currentResult: Flow<PagingData<Movie>>? = null

    fun getMovies(apiKey: String): Flow<PagingData<Movie>> {
        val lastResult = currentResult
        if (lastResult != null) {
            return lastResult
        }

        val newResult: Flow<PagingData<Movie>> = repository.getMovies(apiKey)
            .cachedIn(viewModelScope)
        currentResult = newResult

        return newResult
    }
}