package com.ngengeapps.movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ngengeapps.movies.api.TheMoviesDbAPI
import com.ngengeapps.movies.models.Movie
import kotlinx.coroutines.flow.Flow

class MoviesRepository(private val backend: TheMoviesDbAPI) {
    fun getMovies(apiKey: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = { MoviesRemotePagingSource(backend, apiKey) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 30
    }
}