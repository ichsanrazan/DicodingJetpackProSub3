package com.dicoding.jetpackprosub3.ui.favorite.tv

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
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.databinding.ItemsMoviesBinding
import com.dicoding.jetpackprosub3.utils.NetworkInfo

class FavoriteTvAdapter : PagedListAdapter<TvEntity, FavoriteTvAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun getSwipedData(swipedPosition: Int): TvEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemsMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemGenre.text = tvShow.voteAverage.toString()

                Glide.with(binding.root.context)
                    .asBitmap()
                    .load(NetworkInfo.IMAGE_URL + tvShow.posterPath)
                    .transform(RoundedCorners(28))
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            imgPoster.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })

                itemView.setOnClickListener {

                }

                itemView.setOnClickListener{onItemClickCallback.onItemClicked(tvShow.id.toString())}
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}