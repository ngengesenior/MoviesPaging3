package com.ngengeapps.movies

import androidx.paging.PagingSource
import com.ngengeapps.movies.api.TheMoviesDbAPI
import com.ngengeapps.movies.models.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviesRemotePagingSource(private val backend: TheMoviesDbAPI, private val apiKey: String) :
    PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response =
                backend.getPopularMovies(page, apiKey)
            LoadResult.Page(
                data = response.results,
                prevKey = response.page - 1,
                nextKey = response.page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (httpExc: HttpException) {
            return LoadResult.Error(httpExc)
        }
    }
}