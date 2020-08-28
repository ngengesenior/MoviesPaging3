package com.ngengeapps.movies.models

data class Movie(
    val poster_path:String? = null,
    val overview:String,
    val id:Int,
    val release_date:String,
    val genre_ids:List<Int>,
    val title:String,
    val vote_count:Int,
    val backdrop_path:String?=null,
    val vote_average:Double
)

data class MoviesResult(
    val results:List<Movie>,
    val page:Int,
    val total_results:Int,
    val total_pages:Int
)