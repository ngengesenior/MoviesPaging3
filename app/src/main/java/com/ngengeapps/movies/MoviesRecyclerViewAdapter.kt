package com.ngengeapps.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ngengeapps.movies.models.Movie
import com.squareup.picasso.Picasso

class MoviesRecyclerViewAdapter :
    PagingDataAdapter<Movie, MovieViewHolder>(diffCallback) {


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = getItem(position)
        if (movie == null) {
            holder.bindProgress()
        } else {
            holder.bindTo(getItem(position))
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(parent)
    }

}


class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
) {
    private val movieImage = itemView.findViewById<ImageView>(R.id.movie_image)
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    var movie: Movie? = null

    fun bindTo(movie: Movie?) {
        this.movie = movie
        movie?.poster_path?.let {
            Picasso.get().load("https://image.tmdb.org/t/p/w500$it").into(movieImage)
        }

    }

    fun bindProgress() {
        progressBar.visibility = View.INVISIBLE
    }
}
