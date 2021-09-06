package com.dicoding.jetpackprosub3.ui.favorite.movies

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.databinding.ItemsMoviesBinding
import com.dicoding.jetpackprosub3.utils.NetworkInfo

class FavoriteMoviesAdapter : PagedListAdapter<MoviesEntity, FavoriteMoviesAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun getSwipedData(swipedPosition: Int): MoviesEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val binding: ItemsMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemGenre.text = movie.voteAverage.toString()

                Glide.with(itemView.context)
                    .asBitmap()
                    .load(NetworkInfo.IMAGE_URL + movie.posterPath)
                    .transform(RoundedCorners(28))
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            imgPoster.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })

                itemView.setOnClickListener {onItemClickCallback.onItemClicked(movie.id.toString())}
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}