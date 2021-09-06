package com.dicoding.jetpackprosub3.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dicoding.jetpackprosub3.R
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.databinding.ActivityDetailBinding
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel.Companion.MOVIE
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel.Companion.TV
import com.dicoding.jetpackprosub3.utils.NetworkInfo.IMAGE_URL
import com.dicoding.jetpackprosub3.viewmodel.ViewModelFactory
import com.dicoding.jetpackprosub3.vo.Status

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val FILM = "film"
        const val CATEGORY = "category"
    }

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var dataCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.hide()

        showProgressBar(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        detailBinding.fabAddFav.setOnClickListener(this)

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(FILM)
            dataCategory = extras.getString(CATEGORY)

            if (dataId != null && dataCategory != null) {
                viewModel.setFilm(dataId, dataCategory.toString())
                setupState()
                if (dataCategory == MOVIE) {
                    viewModel.getDetailMovie().observe(this, { detail ->
                        when (detail.status) {
                            Status.LOADING -> showProgressBar(true)
                            Status.SUCCESS -> {
                                if (detail.data != null) {
                                    showProgressBar(false)
                                    populateDataDetail(detail.data)
                                }
                            }
                            Status.ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } else if (dataCategory == TV) {
                    viewModel.getDetailTvShow().observe(this, { detail ->
                        when (detail.status) {
                            Status.LOADING -> showProgressBar(true)
                            Status.SUCCESS -> {
                                if (detail.data != null) {
                                    showProgressBar(false)
                                    populateDataDetail(detail.data)
                                }
                            }
                            Status.ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_add_fav -> {
                onFabClicked()
            }
        }
    }

    @JvmName("populateDataDetailForMovie")
    private fun populateDataDetail(movie: MoviesEntity) {
        with(movie) {
            val genreDurationText = resources.getString(R.string.genre_duration_text, this.genres, this.runtime.toString())

            detailBinding.tvDetailGenreDuration.text = genreDurationText
            detailBinding.textTitle.text = this.title
            detailBinding.detailName.text = this.overview

            Glide.with(this@DetailActivity)
                .asBitmap()
                .load(IMAGE_URL + this.posterPath)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        detailBinding.circleimageMovies.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })

            detailBinding.circleimageMovies.tag = this.posterPath

            showProgressBar(false)
        }
    }

    @JvmName("populateDataDetailForTvShow")
    private fun populateDataDetail(tvShow: TvEntity) {
        with(tvShow) {
            val genreDurationText = resources.getString(R.string.genre_duration_text, this.genres, this.runtime.toString())

            detailBinding.tvDetailGenreDuration.text = genreDurationText
            detailBinding.textTitle.text = this.name
            detailBinding.detailName.text = this.overview

            Glide.with(this@DetailActivity)
                .asBitmap()
                .load(IMAGE_URL + this.posterPath)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        detailBinding.circleimageMovies.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })

            detailBinding.circleimageMovies.tag = this.posterPath

            showProgressBar(false)
        }
    }

    private fun setupState() {
        if (dataCategory == MOVIE) {
            viewModel.getDetailMovie().observe(this, { movie ->
                when (movie.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            showProgressBar(false)
                            val state = movie.data.isFav
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (dataCategory == TV) {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            showProgressBar(false)
                            val state = tvShow.data.isFav
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun onFabClicked() {
        if (dataCategory == MOVIE) {
            viewModel.setFavoriteMovie()
        } else if (dataCategory == TV) {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun setFavoriteState(state: Boolean) {
        val fab = detailBinding.fabAddFav
        if (state) {
            fab.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            fab.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun showProgressBar(state: Boolean) {
        detailBinding.progressBar.isVisible = state
        detailBinding.fabAddFav.isInvisible = state
    }
}