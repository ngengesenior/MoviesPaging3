package com.ngengeapps.movies.api

import com.ngengeapps.movies.models.MoviesResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface TheMoviesDbAPI {


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): MoviesResult


    companion object {
        private const val MOVIES_BASE_URL = "https://api.themoviedb.org/3/"
        fun create(): TheMoviesDbAPI {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)


            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            return Retrofit.Builder()
                .baseUrl(MOVIES_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMoviesDbAPI::class.java)
        }
    }
}

