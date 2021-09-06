package com.dicoding.jetpackprosub3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.vo.Resource

class DetailViewModel(private val movieCatalogueRepository: DataRepository) : ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV = "tv"
    }

    private lateinit var detTvShow: LiveData<Resource<TvEntity>>
    private lateinit var detMovie: LiveData<Resource<MoviesEntity>>

    fun setFilm(id: String, category: String) {
        when (category) {
            TV -> {
                detTvShow = movieCatalogueRepository.getDetailTvShow(id.toInt())
            }

            MOVIE -> {
                detMovie = movieCatalogueRepository.getDetailMovie(id.toInt())
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            movieCatalogueRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            movieCatalogueRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailTvShow() = detTvShow
    fun getDetailMovie() = detMovie

}