package com.dicoding.jetpackprosub3.ui.favorite.movies

import androidx.lifecycle.ViewModel
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity

class FavoriteMoviesViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getFavMovies() = dataRepository.getFavoriteMovies()

    fun setFavMovie(movieEntity: MoviesEntity) {
        val newState = !movieEntity.isFav
        dataRepository.setFavoriteMovie(movieEntity, newState)
    }
}